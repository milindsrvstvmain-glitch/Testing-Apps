package com.example.ui.screens

import android.content.Intent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.automirrored.rounded.VolumeUp
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.SportsEsports
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.GraphicEq
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.MenuBook
import androidx.compose.material.icons.rounded.School
import androidx.compose.material.icons.rounded.SlowMotionVideo
import androidx.compose.material.icons.rounded.SportsEsports
import androidx.compose.material.icons.rounded.Stop
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.LearningPhrase
import com.example.model.LessonUnit
import com.example.model.SupportedLanguage
import com.example.ui.MainTab
import com.example.ui.PolyglotUiState
import com.example.ui.components.DuolingoButton
import com.example.ui.components.SoundWaveIndicator
import com.example.ui.components.StepProgressBar
import com.example.ui.components.TactileCard
import com.example.ui.theme.AmberGold
import com.example.ui.theme.AmberGoldSoft
import com.example.ui.theme.FeatherGreen
import com.example.ui.theme.FeatherGreenDark
import com.example.ui.theme.FeatherGreenSoft
import com.example.ui.theme.SkyBlue
import com.example.ui.theme.SkyBlueDark
import com.example.ui.theme.SkyBlueSoft

@Composable
fun MainLearningScreen(
    uiState: PolyglotUiState,
    onSelectTab: (MainTab) -> Unit,
    onSelectUnitAndPhrase: (Int, Int) -> Unit,
    onNextPhrase: () -> Unit,
    onPreviousPhrase: () -> Unit,
    onSpeakTarget: (unitId: String, phraseId: String, language: SupportedLanguage, text: String, slow: Boolean) -> Unit,
    onSpeakNative: (phraseId: String, language: SupportedLanguage, text: String) -> Unit,
    onTogglePlayAll: () -> Unit,
    onToggleSlowNarration: () -> Unit,
    onSelectLessonLanguage: (String) -> Unit,
    onSpeakLessonWord: (key: String, language: SupportedLanguage, text: String) -> Unit,
    onSpeakQuizAudio: (key: String, language: SupportedLanguage, text: String) -> Unit,
    onOpenEditLanguages: () -> Unit,
    onCloseEditLanguages: () -> Unit,
    onToggleDraftLanguage: (String) -> Unit,
    onSelectDraftNative: (String) -> Unit,
    onSaveEditedLanguages: () -> Unit,
    onDismissVoiceWarning: () -> Unit,
    onBuildVoiceInstallIntent: () -> Intent,
    onSelectQuizOption: (String) -> Unit,
    onCheckQuizAnswer: () -> Unit,
    onNextQuizQuestion: () -> Unit,
    onRestartQuiz: () -> Unit
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val useRail = maxWidth >= 600.dp

        Scaffold(
            contentWindowInsets = WindowInsets.safeDrawing,
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                PolyglotTopBar(
                    username = uiState.profile.username,
                    xp = uiState.profile.xp,
                    streak = uiState.profile.streakDays,
                    targetLanguages = uiState.targetLanguages,
                    onEditLanguages = onOpenEditLanguages
                )
            },
            bottomBar = {
                if (!useRail) {
                    PolyglotBottomBar(
                        activeTab = uiState.activeTab,
                        onSelectTab = onSelectTab
                    )
                }
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (useRail) {
                    PolyglotNavigationRail(
                        activeTab = uiState.activeTab,
                        onSelectTab = onSelectTab
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    when (uiState.activeTab) {
                        MainTab.LEARN -> MultiLanguageLessonStage(
                            uiState = uiState,
                            onSelectPhraseIndex = { phraseIdx ->
                                onSelectUnitAndPhrase(uiState.currentUnitIndex, phraseIdx)
                            },
                            onNextPhrase = onNextPhrase,
                            onPreviousPhrase = onPreviousPhrase,
                            onSpeakTarget = onSpeakTarget,
                            onSpeakNative = onSpeakNative,
                            onTogglePlayAll = onTogglePlayAll,
                            onToggleSlowNarration = onToggleSlowNarration,
                            onDismissVoiceWarning = onDismissVoiceWarning,
                            onBuildVoiceInstallIntent = onBuildVoiceInstallIntent,
                            onOpenPracticeQuiz = { onSelectTab(MainTab.PRACTICE) }
                        )

                        MainTab.LESSON -> LessonGuideScreen(
                            uiState = uiState,
                            selectedLanguageCode = uiState.selectedLessonLanguageCode,
                            onSelectLanguageCode = onSelectLessonLanguage,
                            onToggleSlowNarration = onToggleSlowNarration,
                            onSpeakLessonWord = onSpeakLessonWord
                        )

                        MainTab.PATH -> CoursePathScreen(
                            uiState = uiState,
                            onSelectUnitAndPhrase = onSelectUnitAndPhrase
                        )

                        MainTab.PRACTICE -> PracticeQuizScreen(
                            uiState = uiState,
                            onSpeakQuizAudio = { key, lang, text ->
                                onSpeakQuizAudio(key, lang, text)
                            },
                            onSelectOption = onSelectQuizOption,
                            onCheckAnswer = onCheckQuizAnswer,
                            onNextQuestion = onNextQuizQuestion,
                            onRestartQuiz = onRestartQuiz
                        )
                    }
                }
            }
        }

        if (uiState.showEditLanguagesDialog) {
            EditLanguagesDialog(
                draftTargets = uiState.draftTargetLanguages,
                draftNative = uiState.draftNativeLanguage,
                limitNotice = uiState.limitNotice,
                onToggleTarget = onToggleDraftLanguage,
                onSelectNative = onSelectDraftNative,
                onDismiss = onCloseEditLanguages,
                onSave = onSaveEditedLanguages
            )
        }
    }
}

@Composable
private fun PolyglotTopBar(
    username: String,
    xp: Int,
    streak: Int,
    targetLanguages: List<SupportedLanguage>,
    onEditLanguages: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.7f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_mascot_welcome),
                    contentDescription = "Polyglot Master badge",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                )
                Column {
                    Text(
                        text = "Hi, ${username.ifEmpty { "Learner" }}",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = targetLanguages.joinToString(" ") { it.flag },
                        fontSize = 14.sp
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Streak Pill
                Surface(
                    shape = RoundedCornerShape(999.dp),
                    color = AmberGoldSoft
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.LocalFireDepartment,
                            contentDescription = "Streak",
                            tint = AmberGold,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "$streak",
                            style = MaterialTheme.typography.labelMedium,
                            color = AmberGold
                        )
                    }
                }

                // XP Pill
                Surface(
                    shape = RoundedCornerShape(999.dp),
                    color = SkyBlueSoft
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Bolt,
                            contentDescription = "XP",
                            tint = SkyBlueDark,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "$xp",
                            style = MaterialTheme.typography.labelMedium,
                            color = SkyBlueDark
                        )
                    }
                }

                // Edit Languages button
                Surface(
                    onClick = onEditLanguages,
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.background,
                    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.outline),
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .testTag("edit_languages_button")
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Tune,
                        contentDescription = "Edit languages",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(8.dp).size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun PolyglotBottomBar(
    activeTab: MainTab,
    onSelectTab: (MainTab) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp
    ) {
        val navItems = listOf(
            Triple(MainTab.LEARN, "Learn", Pair(Icons.Rounded.School, Icons.Outlined.School)),
            Triple(MainTab.LESSON, "Lesson", Pair(Icons.Rounded.MenuBook, Icons.Outlined.MenuBook)),
            Triple(MainTab.PATH, "Units", Pair(Icons.Rounded.Explore, Icons.Outlined.Explore)),
            Triple(MainTab.PRACTICE, "Quiz", Pair(Icons.Rounded.SportsEsports, Icons.Outlined.SportsEsports))
        )
        navItems.forEach { (tab, label, icons) ->
            val selected = activeTab == tab
            NavigationBarItem(
                selected = selected,
                onClick = { onSelectTab(tab) },
                icon = {
                    Icon(
                        imageVector = if (selected) icons.first else icons.second,
                        contentDescription = label
                    )
                },
                label = { Text(label, style = MaterialTheme.typography.labelMedium) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = FeatherGreenDark,
                    selectedTextColor = FeatherGreenDark,
                    indicatorColor = FeatherGreenSoft
                ),
                modifier = Modifier.testTag("nav_${tab.name.lowercase()}")
            )
        }
    }
}

@Composable
private fun PolyglotNavigationRail(
    activeTab: MainTab,
    onSelectTab: (MainTab) -> Unit
) {
    NavigationRail(
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        val navItems = listOf(
            Triple(MainTab.LEARN, "Learn", Pair(Icons.Rounded.School, Icons.Outlined.School)),
            Triple(MainTab.LESSON, "Lesson", Pair(Icons.Rounded.MenuBook, Icons.Outlined.MenuBook)),
            Triple(MainTab.PATH, "Units", Pair(Icons.Rounded.Explore, Icons.Outlined.Explore)),
            Triple(MainTab.PRACTICE, "Quiz", Pair(Icons.Rounded.SportsEsports, Icons.Outlined.SportsEsports))
        )
        Spacer(modifier = Modifier.height(12.dp))
        navItems.forEach { (tab, label, icons) ->
            val selected = activeTab == tab
            NavigationRailItem(
                selected = selected,
                onClick = { onSelectTab(tab) },
                icon = {
                    Icon(
                        imageVector = if (selected) icons.first else icons.second,
                        contentDescription = label
                    )
                },
                label = { Text(label, style = MaterialTheme.typography.labelMedium) },
                colors = NavigationRailItemDefaults.colors(
                    selectedIconColor = FeatherGreenDark,
                    selectedTextColor = FeatherGreenDark,
                    indicatorColor = FeatherGreenSoft
                )
            )
        }
    }
}

@Composable
private fun MultiLanguageLessonStage(
    uiState: PolyglotUiState,
    onSelectPhraseIndex: (Int) -> Unit,
    onNextPhrase: () -> Unit,
    onPreviousPhrase: () -> Unit,
    onSpeakTarget: (unitId: String, phraseId: String, language: SupportedLanguage, text: String, slow: Boolean) -> Unit,
    onSpeakNative: (phraseId: String, language: SupportedLanguage, text: String) -> Unit,
    onTogglePlayAll: () -> Unit,
    onToggleSlowNarration: () -> Unit,
    onDismissVoiceWarning: () -> Unit,
    onBuildVoiceInstallIntent: () -> Intent,
    onOpenPracticeQuiz: () -> Unit
) {
    val context = LocalContext.current
    val unit = uiState.currentUnit
    val phrase = uiState.currentPhrase
    val nativeLang = uiState.nativeLanguage
    val username = uiState.profile.username
    val nativeTranslation = phrase.translationFor(nativeLang.code)
    val nativeDisplayText = nativeTranslation.formattedText(username)

    LazyColumn(
        modifier = Modifier
            .widthIn(max = 680.dp)
            .fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // 1. Unit Hero Banner with Progress
        item(key = "unit_banner") {
            UnitHeroBanner(
                unit = unit,
                phraseIndex = uiState.currentPhraseIndex,
                onSelectPhraseIndex = onSelectPhraseIndex
            )
        }

        // Beginner-friendly Slow Narration & Listen-All toolbar
        item(key = "narration_controls_bar") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    onClick = onToggleSlowNarration,
                    shape = RoundedCornerShape(16.dp),
                    color = if (uiState.slowNarrationEnabled) AmberGoldSoft else MaterialTheme.colorScheme.surface,
                    border = BorderStroke(
                        1.5.dp,
                        if (uiState.slowNarrationEnabled) AmberGold else MaterialTheme.colorScheme.outline
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .minimumInteractiveComponentSize()
                        .testTag("slow_narration_toggle")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.SlowMotionVideo,
                            contentDescription = "Toggle slow narration",
                            tint = if (uiState.slowNarrationEnabled) AmberGold else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (uiState.slowNarrationEnabled) {
                                "Slow Narration: ON"
                            } else {
                                "Slow Narration: OFF"
                            },
                            style = MaterialTheme.typography.labelMedium,
                            color = if (uiState.slowNarrationEnabled) AmberGold else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Surface(
                    onClick = onTogglePlayAll,
                    shape = RoundedCornerShape(16.dp),
                    color = if (uiState.isPlayingAll) SkyBlue else SkyBlueSoft,
                    border = BorderStroke(1.5.dp, SkyBlue),
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .testTag("narration_hear_all_chip")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = if (uiState.isPlayingAll) Icons.Rounded.Stop else Icons.AutoMirrored.Rounded.VolumeUp,
                            contentDescription = "Narrate all target languages",
                            tint = if (uiState.isPlayingAll) Color.White else SkyBlueDark,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = if (uiState.isPlayingAll) {
                                "Stop"
                            } else if (uiState.slowNarrationEnabled) {
                                "Slow All ${uiState.targetLanguages.size}"
                            } else {
                                "Hear All ${uiState.targetLanguages.size}"
                            },
                            style = MaterialTheme.typography.labelMedium,
                            color = if (uiState.isPlayingAll) Color.White else SkyBlueDark
                        )
                    }
                }
            }
        }

        // Optional TTS voice pack helper banner
        if (uiState.voiceWarning != null) {
            item(key = "tts_warning") {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = AmberGoldSoft,
                    border = BorderStroke(1.5.dp, AmberGold),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = uiState.voiceWarning,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f)
                        )
                        Row {
                            TextButton(
                                onClick = {
                                    runCatching {
                                        context.startActivity(onBuildVoiceInstallIntent())
                                    }
                                    onDismissVoiceWarning()
                                }
                            ) {
                                Text("Voice Setup", fontWeight = FontWeight.Bold)
                            }
                            TextButton(onClick = onDismissVoiceWarning) {
                                Text("OK")
                            }
                        }
                    }
                }
            }
        }

        // 2. Top Native Language Card (e.g., English "Hello" on top!)
        item(key = "native_prompt_${phrase.id}") {
            val isSpeakingNative = uiState.speakingKey == "native_${phrase.id}_${nativeLang.code}"
            AnimatedContent(
                targetState = nativeDisplayText,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "nativePromptAnim"
            ) { promptText ->
                TactileCard(
                    onClick = { onSpeakNative(phrase.id, nativeLang, promptText) },
                    containerColor = MaterialTheme.colorScheme.surface,
                    borderColor = if (isSpeakingNative) SkyBlue else FeatherGreen.copy(alpha = 0.65f),
                    lipColor = if (isSpeakingNative) SkyBlueDark else FeatherGreenDark,
                    cornerRadius = 22.dp,
                    tag = "native_prompt_card"
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        FeatherGreenSoft.copy(alpha = 0.42f),
                                        MaterialTheme.colorScheme.surface
                                    )
                                )
                            )
                            .padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(999.dp),
                                    color = FeatherGreenSoft
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Text(text = nativeLang.flag, fontSize = 16.sp)
                                        Text(
                                            text = "${nativeLang.englishName.uppercase()} (YOUR LANGUAGE)",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = FeatherGreenDark
                                        )
                                    }
                                }

                                if (phrase.speakerRole != null) {
                                    val isAgent = phrase.speakerRole.equals("Agent", ignoreCase = true)
                                    Surface(
                                        shape = RoundedCornerShape(999.dp),
                                        color = if (isAgent) SkyBlueSoft else AmberGoldSoft
                                    ) {
                                        Text(
                                            text = if (isAgent) "✈️ AGENT" else "🧳 TRAVELER",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = if (isAgent) SkyBlueDark else AmberGold,
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                                        )
                                    }
                                }
                            }

                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.VolumeUp,
                                contentDescription = "Hear ${nativeLang.englishName} prompt",
                                tint = FeatherGreenDark,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Text(
                            text = promptText,
                            style = if (promptText.length > 36) {
                                MaterialTheme.typography.headlineMedium
                            } else {
                                MaterialTheme.typography.displayMedium
                            },
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = if (uiState.slowNarrationEnabled) {
                                "Slow narration active (0.7x) — tap any card below to hear clearly"
                            } else {
                                "Tap any language card below to hear native pronunciation"
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // 3. The 3 Target Language Versions Stacked Below
        itemsIndexed(
            items = uiState.targetLanguages,
            key = { _, lang -> "${phrase.id}_${lang.code}" }
        ) { _, targetLang ->
            val translation = phrase.translationFor(targetLang.code)
            val displayText = translation.formattedText(username)
            val phoneticText = translation.formattedPhonetic(username)
            val speakingKey = "${phrase.id}_${targetLang.code}"
            val isSpeaking = uiState.speakingKey == speakingKey
            val isHeard = uiState.profile.heardPhraseKeys.contains("${unit.id}:${phrase.id}:${targetLang.code}")

            TargetLanguageAudioCard(
                language = targetLang,
                phraseText = displayText,
                phoneticText = phoneticText,
                usageNote = translation.usageNote,
                isSpeaking = isSpeaking,
                isHeard = isHeard,
                onTapSpeak = {
                    onSpeakTarget(unit.id, phrase.id, targetLang, displayText, false)
                },
                onTapSlowSpeak = {
                    onSpeakTarget(unit.id, phrase.id, targetLang, displayText, true)
                }
            )
        }

        // 4. Bottom Stage Navigation Controls
        item(key = "stage_controls") {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DuolingoButton(
                        text = stringResource(R.string.prev_phrase),
                        enabled = uiState.currentUnitIndex > 0 || uiState.currentPhraseIndex > 0,
                        containerColor = MaterialTheme.colorScheme.surface,
                        lipColor = MaterialTheme.colorScheme.outline,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                        onClick = onPreviousPhrase,
                        modifier = Modifier.weight(0.9f),
                        tag = "prev_phrase_button"
                    )

                    DuolingoButton(
                        text = when {
                            uiState.isPlayingAll -> "Stop Audio"
                            uiState.slowNarrationEnabled -> "Slow All ${uiState.targetLanguages.size}"
                            else -> "Hear All ${uiState.targetLanguages.size}"
                        },
                        containerColor = SkyBlue,
                        lipColor = SkyBlueDark,
                        onClick = onTogglePlayAll,
                        modifier = Modifier.weight(1.1f),
                        tag = "hear_all_button"
                    )

                    DuolingoButton(
                        text = stringResource(R.string.next_phrase),
                        onClick = onNextPhrase,
                        modifier = Modifier.weight(1f),
                        tag = "next_phrase_button"
                    )
                }

                // Helpful quick link to practice quiz at the end of the Greetings unit
                AnimatedVisibility(visible = uiState.currentPhraseIndex == unit.phrases.lastIndex) {
                    TactileCard(
                        onClick = onOpenPracticeQuiz,
                        containerColor = AmberGoldSoft,
                        borderColor = AmberGold,
                        lipColor = AmberGold,
                        cornerRadius = 18.dp,
                        tag = "quick_quiz_banner"
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Ready to test your ${unit.title}?",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Play a 5-question multi-language audio quiz for +95 XP!",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                                contentDescription = "Open quiz",
                                tint = AmberGold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun UnitHeroBanner(
    unit: LessonUnit,
    phraseIndex: Int,
    onSelectPhraseIndex: (Int) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(22.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(82.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_hero_greetings),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                listOf(
                                    FeatherGreenDark.copy(alpha = 0.88f),
                                    FeatherGreen.copy(alpha = 0.78f)
                                )
                            )
                        )
                )
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "UNIT ${unit.number} • ${unit.title.uppercase()}",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White.copy(alpha = 0.92f)
                        )
                        Text(
                            text = "${unit.emoji} ${unit.subtitle}",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color.White.copy(alpha = 0.22f)
                    ) {
                        Text(
                            text = "${phraseIndex + 1} / ${unit.phrases.size}",
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StepProgressBar(
                    progress = (phraseIndex + 1).toFloat() / unit.phrases.size.toFloat(),
                    modifier = Modifier.fillMaxWidth()
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(unit.phrases) { index, item ->
                        val isCurrent = index == phraseIndex
                        Surface(
                            onClick = { onSelectPhraseIndex(index) },
                            shape = RoundedCornerShape(999.dp),
                            color = if (isCurrent) FeatherGreen else MaterialTheme.colorScheme.background,
                            border = BorderStroke(
                                1.5.dp,
                                if (isCurrent) FeatherGreenDark else MaterialTheme.colorScheme.outline
                            )
                        ) {
                            Text(
                                text = item.shortTitle,
                                style = MaterialTheme.typography.labelMedium,
                                color = if (isCurrent) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TargetLanguageAudioCard(
    language: SupportedLanguage,
    phraseText: String,
    phoneticText: String,
    usageNote: String,
    isSpeaking: Boolean,
    isHeard: Boolean,
    onTapSpeak: () -> Unit,
    onTapSlowSpeak: () -> Unit
) {
    val activeBorder = if (isSpeaking) language.accentColor else MaterialTheme.colorScheme.outline
    val activeLip = if (isSpeaking) language.accentColor else MaterialTheme.colorScheme.outline
    val cardBg = if (isSpeaking) {
        language.accentSoftColor.copy(alpha = 0.45f)
    } else {
        MaterialTheme.colorScheme.surface
    }

    TactileCard(
        onClick = onTapSpeak,
        containerColor = cardBg,
        borderColor = activeBorder,
        lipColor = activeLip,
        cornerRadius = 20.dp,
        tag = "target_card_${language.code}"
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(999.dp),
                        color = language.accentSoftColor
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(text = language.flag, fontSize = 16.sp)
                            Text(
                                text = "${language.englishName} · ${language.nativeName}",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    if (isHeard) {
                        Icon(
                            imageVector = Icons.Rounded.CheckCircle,
                            contentDescription = "Practiced",
                            tint = FeatherGreen,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                // Slow 0.7x native audio button
                Surface(
                    onClick = onTapSlowSpeak,
                    shape = RoundedCornerShape(999.dp),
                    color = MaterialTheme.colorScheme.background,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                    modifier = Modifier.testTag("slow_audio_${language.code}")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.SlowMotionVideo,
                            contentDescription = "Play slowly in ${language.englishName}",
                            tint = language.accentColor,
                            modifier = Modifier.size(15.dp)
                        )
                        Text(
                            text = "0.7x",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Text(
                        text = phraseText,
                        style = if (phraseText.length > 42) {
                            MaterialTheme.typography.titleLarge
                        } else {
                            MaterialTheme.typography.headlineLarge
                        },
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    if (phoneticText.isNotBlank()) {
                        Text(
                            text = "/$phoneticText/",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = language.accentColor
                        )
                    }
                    if (usageNote.isNotBlank()) {
                        Text(
                            text = usageNote,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Surface(
                    shape = CircleShape,
                    color = if (isSpeaking) language.accentColor else language.accentSoftColor,
                    modifier = Modifier.size(50.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        if (isSpeaking) {
                            SoundWaveIndicator(
                                isActive = true,
                                color = Color.White
                            )
                        } else {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.VolumeUp,
                                contentDescription = "Speak $phraseText in ${language.englishName}",
                                tint = language.accentColor,
                                modifier = Modifier.size(26.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
