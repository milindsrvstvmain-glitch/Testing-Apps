package com.example.ui

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.NativeSpeechManager
import com.example.audio.TtsAvailability
import com.example.data.CurriculumCatalog
import com.example.data.UserPreferencesRepository
import com.example.model.LanguageCatalog
import com.example.model.LearningPhrase
import com.example.model.LessonUnit
import com.example.model.SupportedLanguage
import com.example.model.UserProfile
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class MainTab {
    LEARN,
    LESSON,
    PATH,
    PRACTICE
}

data class QuizQuestion(
    val phrase: LearningPhrase,
    val targetLanguage: SupportedLanguage,
    val correctText: String,
    val options: List<String>
)

data class QuizUiState(
    val questions: List<QuizQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val selectedOption: String? = null,
    val isAnswerChecked: Boolean = false,
    val isCorrect: Boolean = false,
    val score: Int = 0,
    val isFinished: Boolean = false
)

data class PolyglotUiState(
    val isLoaded: Boolean = false,
    val profile: UserProfile = UserProfile(),
    val draftUsername: String = "",
    val draftTargetLanguages: List<String> = emptyList(),
    val draftNativeLanguage: String = "en",
    val limitNotice: String? = null,
    val currentUnitIndex: Int = 0,
    val currentPhraseIndex: Int = 0,
    val activeTab: MainTab = MainTab.LEARN,
    val speakingKey: String? = null,
    val ttsAvailability: TtsAvailability = TtsAvailability.Initializing,
    val voiceWarning: String? = null,
    val isPlayingAll: Boolean = false,
    val slowNarrationEnabled: Boolean = false,
    val selectedLessonLanguageCode: String = "",
    val showEditLanguagesDialog: Boolean = false,
    val quizState: QuizUiState = QuizUiState()
) {
    val units: List<LessonUnit> get() = CurriculumCatalog.units
    val currentUnit: LessonUnit get() = units.getOrElse(currentUnitIndex) { units.first() }
    val currentPhrase: LearningPhrase
        get() = currentUnit.phrases.getOrElse(currentPhraseIndex) { currentUnit.phrases.first() }

    val nativeLanguage: SupportedLanguage
        get() = LanguageCatalog.findByCode(profile.nativeLanguageCode)

    val targetLanguages: List<SupportedLanguage>
        get() = profile.targetLanguageCodes
            .ifEmpty { listOf("fr", "es", "vi") }
            .map { LanguageCatalog.findByCode(it) }
}

class PolyglotViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserPreferencesRepository(application)
    private val speechManager = NativeSpeechManager(application)

    private val _localState = MutableStateFlow(LocalUiDraft())
    private var playAllJob: Job? = null

    private data class LocalUiDraft(
        val draftUsername: String = "",
        val draftTargetLanguages: List<String> = emptyList(),
        val draftNativeLanguage: String = "en",
        val limitNotice: String? = null,
        val currentUnitIndex: Int = 0,
        val currentPhraseIndex: Int = 0,
        val activeTab: MainTab = MainTab.LEARN,
        val isPlayingAll: Boolean = false,
        val slowNarrationEnabled: Boolean = false,
        val selectedLessonLanguageCode: String = "",
        val showEditLanguagesDialog: Boolean = false,
        val quizState: QuizUiState = QuizUiState()
    )

    val uiState: StateFlow<PolyglotUiState> = combine(
        repository.userProfileFlow,
        _localState,
        speechManager.speakingKey,
        speechManager.availability,
        speechManager.voiceWarning
    ) { profile, local, speakingKey, ttsAvail, warning ->
        PolyglotUiState(
            isLoaded = true,
            profile = profile,
            draftUsername = local.draftUsername,
            draftTargetLanguages = local.draftTargetLanguages,
            draftNativeLanguage = local.draftNativeLanguage,
            limitNotice = local.limitNotice,
            currentUnitIndex = local.currentUnitIndex,
            currentPhraseIndex = local.currentPhraseIndex,
            activeTab = local.activeTab,
            speakingKey = speakingKey,
            ttsAvailability = ttsAvail,
            voiceWarning = warning,
            isPlayingAll = local.isPlayingAll,
            slowNarrationEnabled = local.slowNarrationEnabled,
            selectedLessonLanguageCode = local.selectedLessonLanguageCode,
            showEditLanguagesDialog = local.showEditLanguagesDialog,
            quizState = local.quizState
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = PolyglotUiState()
    )

    fun updateDraftUsername(name: String) {
        _localState.update { it.copy(draftUsername = name, limitNotice = null) }
    }

    fun toggleDraftTargetLanguage(code: String) {
        _localState.update { state ->
            val current = state.draftTargetLanguages
            if (current.contains(code)) {
                state.copy(
                    draftTargetLanguages = current - code,
                    limitNotice = null
                )
            } else if (current.size >= 3) {
                state.copy(
                    limitNotice = "Maximum 3 languages! Tap a selected language to swap."
                )
            } else {
                val updatedTargets = current + code
                val adjustedNative = if (state.draftNativeLanguage == code) {
                    LanguageCatalog.nativePriorityOrder
                        .firstOrNull { !updatedTargets.contains(it.code) }?.code ?: "en"
                } else {
                    state.draftNativeLanguage
                }
                state.copy(
                    draftTargetLanguages = updatedTargets,
                    draftNativeLanguage = adjustedNative,
                    limitNotice = null
                )
            }
        }
    }

    fun selectDraftNativeLanguage(code: String) {
        _localState.update { state ->
            state.copy(draftNativeLanguage = code, limitNotice = null)
        }
    }

    fun clearLimitNotice() {
        _localState.update { it.copy(limitNotice = null) }
    }

    fun completeOnboarding() {
        val snapshot = _localState.value
        val cleanName = snapshot.draftUsername.trim().ifEmpty { "Polyglot" }
        val cleanTargets = snapshot.draftTargetLanguages
            .filter { it != snapshot.draftNativeLanguage }
            .ifEmpty { listOf("fr", "es", "vi") }
            .take(3)

        viewModelScope.launch {
            repository.saveOnboardingProfile(
                username = cleanName,
                nativeLanguageCode = snapshot.draftNativeLanguage,
                targetLanguageCodes = cleanTargets
            )
            _localState.update {
                it.copy(
                    currentUnitIndex = 0,
                    currentPhraseIndex = 0,
                    activeTab = MainTab.LEARN
                )
            }
        }
    }

    fun openEditLanguagesDialog() {
        val currentProfile = uiState.value.profile
        _localState.update {
            it.copy(
                draftUsername = currentProfile.username,
                draftTargetLanguages = currentProfile.targetLanguageCodes,
                draftNativeLanguage = currentProfile.nativeLanguageCode,
                limitNotice = null,
                showEditLanguagesDialog = true
            )
        }
    }

    fun closeEditLanguagesDialog() {
        _localState.update { it.copy(showEditLanguagesDialog = false, limitNotice = null) }
    }

    fun saveEditedLanguages() {
        val snapshot = _localState.value
        val targets = snapshot.draftTargetLanguages
            .filter { it != snapshot.draftNativeLanguage }
            .take(3)
        if (targets.isEmpty()) {
            _localState.update { it.copy(limitNotice = "Pick at least 1 language to learn.") }
            return
        }
        viewModelScope.launch {
            repository.updateLanguages(
                nativeLanguageCode = snapshot.draftNativeLanguage,
                targetLanguageCodes = targets
            )
            _localState.update { it.copy(showEditLanguagesDialog = false, limitNotice = null) }
            if (itTabIsPractice()) {
                startNewQuiz()
            }
        }
    }

    private fun itTabIsPractice(): Boolean = _localState.value.activeTab == MainTab.PRACTICE

    fun selectTab(tab: MainTab) {
        stopPlayAll()
        _localState.update { it.copy(activeTab = tab) }
        if (tab == MainTab.PRACTICE && _localState.value.quizState.questions.isEmpty()) {
            startNewQuiz()
        }
    }

    fun selectUnitAndPhrase(unitIndex: Int, phraseIndex: Int = 0) {
        stopPlayAll()
        _localState.update {
            it.copy(
                currentUnitIndex = unitIndex.coerceIn(0, CurriculumCatalog.units.lastIndex),
                currentPhraseIndex = phraseIndex,
                activeTab = MainTab.LEARN
            )
        }
    }

    fun nextPhrase() {
        stopPlayAll()
        val unit = uiState.value.currentUnit
        val currentIdx = _localState.value.currentPhraseIndex
        if (currentIdx < unit.phrases.lastIndex) {
            _localState.update { it.copy(currentPhraseIndex = currentIdx + 1) }
        } else {
            val nextUnitIdx = (_localState.value.currentUnitIndex + 1) % CurriculumCatalog.units.size
            _localState.update {
                it.copy(currentUnitIndex = nextUnitIdx, currentPhraseIndex = 0)
            }
        }
    }

    fun previousPhrase() {
        stopPlayAll()
        val currentIdx = _localState.value.currentPhraseIndex
        if (currentIdx > 0) {
            _localState.update { it.copy(currentPhraseIndex = currentIdx - 1) }
        } else if (_localState.value.currentUnitIndex > 0) {
            val prevUnitIdx = _localState.value.currentUnitIndex - 1
            val prevUnit = CurriculumCatalog.units[prevUnitIdx]
            _localState.update {
                it.copy(
                    currentUnitIndex = prevUnitIdx,
                    currentPhraseIndex = prevUnit.phrases.lastIndex
                )
            }
        }
    }

    fun selectLessonLanguage(code: String) {
        _localState.update { it.copy(selectedLessonLanguageCode = code) }
    }

    fun speakLessonWord(
        key: String,
        language: SupportedLanguage,
        text: String
    ) {
        stopPlayAll()
        speechManager.speak(
            key = key,
            text = text,
            localeTag = language.localeTag,
            languageName = language.englishName,
            slow = _localState.value.slowNarrationEnabled
        )
    }

    fun toggleSlowNarration() {
        _localState.update { it.copy(slowNarrationEnabled = !it.slowNarrationEnabled) }
    }

    fun speakTranslation(
        unitId: String,
        phraseId: String,
        language: SupportedLanguage,
        text: String,
        slow: Boolean = false
    ) {
        stopPlayAll()
        val useSlow = slow || _localState.value.slowNarrationEnabled
        val key = "${phraseId}_${language.code}"
        speechManager.speak(
            key = key,
            text = text,
            localeTag = language.localeTag,
            languageName = language.englishName,
            slow = useSlow
        )
        viewModelScope.launch {
            repository.recordPhraseHeard("$unitId:$phraseId:${language.code}", xpBonus = 5)
        }
    }

    fun speakNativePrompt(
        phraseId: String,
        language: SupportedLanguage,
        text: String
    ) {
        stopPlayAll()
        speechManager.speak(
            key = "native_${phraseId}_${language.code}",
            text = text,
            localeTag = language.localeTag,
            languageName = language.englishName,
            slow = _localState.value.slowNarrationEnabled
        )
    }

    fun speakQuizText(
        key: String,
        language: SupportedLanguage,
        text: String,
        slow: Boolean = false
    ) {
        stopPlayAll()
        speechManager.speak(
            key = key,
            text = text,
            localeTag = language.localeTag,
            languageName = language.englishName,
            slow = slow
        )
    }

    fun togglePlayAllTargets() {
        if (_localState.value.isPlayingAll) {
            stopPlayAll()
            return
        }
        val state = uiState.value
        val unit = state.currentUnit
        val phrase = state.currentPhrase
        val username = state.profile.username
        val targets = state.targetLanguages
        val useSlow = _localState.value.slowNarrationEnabled

        playAllJob = viewModelScope.launch {
            _localState.update { it.copy(isPlayingAll = true) }
            for (lang in targets) {
                val translation = phrase.translationFor(lang.code).formattedText(username)
                val key = "${phrase.id}_${lang.code}"
                speechManager.speak(
                    key = key,
                    text = translation,
                    localeTag = lang.localeTag,
                    languageName = lang.englishName,
                    slow = useSlow
                )
                repository.recordPhraseHeard("${unit.id}:${phrase.id}:${lang.code}", xpBonus = 5)
                delay(if (useSlow) 2500L else 1800L)
            }
            _localState.update { it.copy(isPlayingAll = false) }
        }
    }

    private fun stopPlayAll() {
        playAllJob?.cancel()
        playAllJob = null
        if (_localState.value.isPlayingAll) {
            _localState.update { it.copy(isPlayingAll = false) }
        }
    }

    fun dismissVoiceWarning() {
        speechManager.clearWarning()
    }

    fun buildVoiceInstallIntent(): Intent = speechManager.buildInstallVoiceIntent()

    // --- Practice Quiz Logic ---

    fun startNewQuiz() {
        val state = uiState.value
        val targets = state.targetLanguages
        val username = state.profile.username
        val allPhrases = CurriculumCatalog.units.flatMap { it.phrases }

        val generated = allPhrases.shuffled().take(5).mapIndexed { index, phrase ->
            val targetLang = targets[index % targets.size]
            val correctText = phrase.translationFor(targetLang.code).formattedText(username)
            val distractors = allPhrases
                .filter { it.id != phrase.id }
                .shuffled()
                .take(3)
                .map { it.translationFor(targetLang.code).formattedText(username) }
                .distinct()
            val options = (listOf(correctText) + distractors).distinct().shuffled()
            QuizQuestion(
                phrase = phrase,
                targetLanguage = targetLang,
                correctText = correctText,
                options = options
            )
        }

        _localState.update {
            it.copy(
                quizState = QuizUiState(questions = generated)
            )
        }
    }

    fun selectQuizOption(option: String) {
        _localState.update { state ->
            if (state.quizState.isAnswerChecked) state
            else state.copy(quizState = state.quizState.copy(selectedOption = option))
        }
    }

    fun checkQuizAnswer() {
        val quiz = _localState.value.quizState
        val currentQ = quiz.questions.getOrNull(quiz.currentIndex) ?: return
        val selected = quiz.selectedOption ?: return
        val isRight = selected == currentQ.correctText

        if (isRight) {
            speechManager.speak(
                key = "quiz_${currentQ.phrase.id}_${currentQ.targetLanguage.code}",
                text = currentQ.correctText,
                localeTag = currentQ.targetLanguage.localeTag,
                languageName = currentQ.targetLanguage.englishName
            )
            viewModelScope.launch {
                repository.addQuizXp(unitId = null, xpEarned = 15)
            }
        }

        _localState.update {
            it.copy(
                quizState = quiz.copy(
                    isAnswerChecked = true,
                    isCorrect = isRight,
                    score = if (isRight) quiz.score + 1 else quiz.score
                )
            )
        }
    }

    fun nextQuizQuestion() {
        val quiz = _localState.value.quizState
        if (quiz.currentIndex < quiz.questions.lastIndex) {
            _localState.update {
                it.copy(
                    quizState = quiz.copy(
                        currentIndex = quiz.currentIndex + 1,
                        selectedOption = null,
                        isAnswerChecked = false,
                        isCorrect = false
                    )
                )
            }
        } else {
            viewModelScope.launch {
                repository.addQuizXp(unitId = uiState.value.currentUnit.id, xpEarned = 20)
            }
            _localState.update {
                it.copy(quizState = quiz.copy(isFinished = true))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        stopPlayAll()
        speechManager.shutdown()
    }
}
