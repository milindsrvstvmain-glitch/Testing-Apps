package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ui.PolyglotUiState
import com.example.ui.PolyglotViewModel
import com.example.ui.screens.MainLearningScreen
import com.example.ui.screens.NativeLanguageScreen
import com.example.ui.screens.SelectLanguagesScreen
import com.example.ui.screens.WelcomeScreen
import com.example.ui.theme.FeatherGreen
import com.example.ui.theme.PolyglotMasterTheme

private object OnboardingRoutes {
    const val WELCOME = "welcome"
    const val SELECT_LANGUAGES = "select_languages"
    const val NATIVE_LANGUAGE = "native_language"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PolyglotMasterTheme {
                PolyglotMasterApp()
            }
        }
    }
}

@Composable
fun PolyglotMasterApp(
    viewModel: PolyglotViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (!uiState.isLoaded) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = FeatherGreen)
        }
        return
    }

    AnimatedContent(
        targetState = uiState.profile.onboardingCompleted,
        transitionSpec = { fadeIn() togetherWith fadeOut() },
        label = "rootTransition"
    ) { completed ->
        if (!completed) {
            OnboardingFlow(
                uiState = uiState,
                viewModel = viewModel
            )
        } else {
            MainLearningScreen(
                uiState = uiState,
                onSelectTab = viewModel::selectTab,
                onSelectUnitAndPhrase = viewModel::selectUnitAndPhrase,
                onNextPhrase = viewModel::nextPhrase,
                onPreviousPhrase = viewModel::previousPhrase,
                onSpeakTarget = viewModel::speakTranslation,
                onSpeakNative = viewModel::speakNativePrompt,
                onTogglePlayAll = viewModel::togglePlayAllTargets,
                onToggleSlowNarration = viewModel::toggleSlowNarration,
                onSelectLessonLanguage = viewModel::selectLessonLanguage,
                onSpeakLessonWord = viewModel::speakLessonWord,
                onSpeakQuizAudio = viewModel::speakQuizText,
                onOpenEditLanguages = viewModel::openEditLanguagesDialog,
                onCloseEditLanguages = viewModel::closeEditLanguagesDialog,
                onToggleDraftLanguage = viewModel::toggleDraftTargetLanguage,
                onSelectDraftNative = viewModel::selectDraftNativeLanguage,
                onSaveEditedLanguages = viewModel::saveEditedLanguages,
                onDismissVoiceWarning = viewModel::dismissVoiceWarning,
                onBuildVoiceInstallIntent = viewModel::buildVoiceInstallIntent,
                onSelectQuizOption = viewModel::selectQuizOption,
                onCheckQuizAnswer = viewModel::checkQuizAnswer,
                onNextQuizQuestion = viewModel::nextQuizQuestion,
                onRestartQuiz = viewModel::startNewQuiz
            )
        }
    }
}

@Composable
private fun OnboardingFlow(
    uiState: PolyglotUiState,
    viewModel: PolyglotViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = OnboardingRoutes.WELCOME
    ) {
        composable(OnboardingRoutes.WELCOME) {
            WelcomeScreen(
                username = uiState.draftUsername,
                onUsernameChange = viewModel::updateDraftUsername,
                onContinue = {
                    navController.navigate(OnboardingRoutes.SELECT_LANGUAGES)
                }
            )
        }

        composable(OnboardingRoutes.SELECT_LANGUAGES) {
            SelectLanguagesScreen(
                username = uiState.draftUsername,
                selectedLanguageCodes = uiState.draftTargetLanguages,
                limitNotice = uiState.limitNotice,
                onToggleLanguage = viewModel::toggleDraftTargetLanguage,
                onBack = {
                    viewModel.clearLimitNotice()
                    navController.popBackStack()
                },
                onContinue = {
                    viewModel.clearLimitNotice()
                    navController.navigate(OnboardingRoutes.NATIVE_LANGUAGE)
                }
            )
        }

        composable(OnboardingRoutes.NATIVE_LANGUAGE) {
            NativeLanguageScreen(
                selectedNativeCode = uiState.draftNativeLanguage,
                selectedTargetCodes = uiState.draftTargetLanguages,
                onSelectNative = viewModel::selectDraftNativeLanguage,
                onBack = { navController.popBackStack() },
                onFinish = viewModel::completeOnboarding
            )
        }
    }
}
