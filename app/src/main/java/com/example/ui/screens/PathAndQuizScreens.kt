package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.VolumeUp
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.LanguageCatalog
import com.example.model.SupportedLanguage
import com.example.ui.PolyglotUiState
import com.example.ui.components.DuolingoButton
import com.example.ui.components.SoundWaveIndicator
import com.example.ui.components.StepProgressBar
import com.example.ui.components.TactileCard
import com.example.ui.theme.AmberGold
import com.example.ui.theme.AmberGoldSoft
import com.example.ui.theme.CoralRed
import com.example.ui.theme.CoralRedDark
import com.example.ui.theme.CoralRedSoft
import com.example.ui.theme.FeatherGreen
import com.example.ui.theme.FeatherGreenDark
import com.example.ui.theme.FeatherGreenSoft
import com.example.ui.theme.RoyalPurple
import com.example.ui.theme.RoyalPurpleSoft
import com.example.ui.theme.SkyBlue
import com.example.ui.theme.SkyBlueDark
import com.example.ui.theme.SkyBlueSoft
import com.example.ui.theme.TealAccent
import com.example.ui.theme.TealAccentSoft

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoursePathScreen(
    uiState: PolyglotUiState,
    onSelectUnitAndPhrase: (unitIndex: Int, phraseIndex: Int) -> Unit
) {
    val unitColors = listOf(
        Pair(FeatherGreen, FeatherGreenSoft),
        Pair(SkyBlue, SkyBlueSoft),
        Pair(RoyalPurple, RoyalPurpleSoft),
        Pair(TealAccent, TealAccentSoft),
        Pair(AmberGold, AmberGoldSoft)
    )

    LazyColumn(
        modifier = Modifier
            .widthIn(max = 640.dp)
            .fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(key = "path_header") {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "Your Multi-Language Path",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Learning ${uiState.targetLanguages.joinToString(", ") { "${it.flag} ${it.englishName}" }}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        itemsIndexed(uiState.units, key = { _, unit -> unit.id }) { unitIdx, unit ->
            val (accent, soft) = unitColors[unitIdx % unitColors.size]
            val totalAudioItems = unit.phrases.size * uiState.targetLanguages.size
            val heardCount = unit.phrases.sumOf { phrase ->
                uiState.targetLanguages.count { lang ->
                    uiState.profile.heardPhraseKeys.contains("${unit.id}:${phrase.id}:${lang.code}")
                }
            }
            val progress = if (totalAudioItems > 0) heardCount.toFloat() / totalAudioItems else 0f
            val isCurrentUnit = unitIdx == uiState.currentUnitIndex

            TactileCard(
                onClick = { onSelectUnitAndPhrase(unitIdx, 0) },
                containerColor = MaterialTheme.colorScheme.surface,
                borderColor = if (isCurrentUnit) accent else MaterialTheme.colorScheme.outline,
                lipColor = if (isCurrentUnit) accent else MaterialTheme.colorScheme.outline,
                cornerRadius = 22.dp,
                tag = "unit_card_${unit.id}"
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(52.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(soft),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = unit.emoji, fontSize = 26.sp)
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "UNIT ${unit.number}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = accent
                                )
                                if (isCurrentUnit) {
                                    Surface(
                                        shape = RoundedCornerShape(999.dp),
                                        color = soft
                                    ) {
                                        Text(
                                            text = "ACTIVE",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = accent,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                            }
                            Text(
                                text = unit.title,
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = unit.subtitle,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        StepProgressBar(
                            progress = progress,
                            fillColor = accent,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "$heardCount/$totalAudioItems",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Phrase stepping-stone chips
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        unit.phrases.forEachIndexed { phraseIdx, phrase ->
                            val isCurrentPhrase = isCurrentUnit && phraseIdx == uiState.currentPhraseIndex
                            val allTargetsHeard = uiState.targetLanguages.all { lang ->
                                uiState.profile.heardPhraseKeys.contains("${unit.id}:${phrase.id}:${lang.code}")
                            }
                            Surface(
                                onClick = { onSelectUnitAndPhrase(unitIdx, phraseIdx) },
                                shape = RoundedCornerShape(12.dp),
                                color = when {
                                    isCurrentPhrase -> accent
                                    allTargetsHeard -> soft
                                    else -> MaterialTheme.colorScheme.background
                                },
                                border = BorderStroke(
                                    1.dp,
                                    if (isCurrentPhrase || allTargetsHeard) accent else MaterialTheme.colorScheme.outline
                                )
                            ) {
                                Text(
                                    text = if (allTargetsHeard && !isCurrentPhrase) {
                                        "✓ ${phrase.shortTitle}"
                                    } else {
                                        phrase.shortTitle
                                    },
                                    style = MaterialTheme.typography.labelMedium,
                                    color = if (isCurrentPhrase) Color.White else MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PracticeQuizScreen(
    uiState: PolyglotUiState,
    onSpeakQuizAudio: (key: String, language: SupportedLanguage, text: String) -> Unit,
    onSelectOption: (String) -> Unit,
    onCheckAnswer: () -> Unit,
    onNextQuestion: () -> Unit,
    onRestartQuiz: () -> Unit
) {
    val quiz = uiState.quizState
    val nativeLang = uiState.nativeLanguage
    val username = uiState.profile.username

    if (quiz.questions.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            DuolingoButton(
                text = "Start Multi-Language Quiz",
                onClick = onRestartQuiz,
                modifier = Modifier.padding(24.dp).widthIn(max = 320.dp)
            )
        }
        return
    }

    if (quiz.isFinished) {
        Column(
            modifier = Modifier
                .widthIn(max = 540.dp)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(AmberGoldSoft),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.EmojiEvents,
                    contentDescription = "Quiz complete",
                    tint = AmberGold,
                    modifier = Modifier.size(54.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Round Complete!",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "You scored ${quiz.score} out of ${quiz.questions.size} across ${uiState.targetLanguages.size} languages!",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(24.dp))
            DuolingoButton(
                text = "Play Another Round",
                onClick = onRestartQuiz,
                modifier = Modifier.fillMaxWidth(),
                tag = "restart_quiz_button"
            )
        }
        return
    }

    val currentQ = quiz.questions[quiz.currentIndex]
    val nativeText = currentQ.phrase.translationFor(nativeLang.code).formattedText(username)

    Column(
        modifier = Modifier
            .widthIn(max = 600.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StepProgressBar(
                    progress = (quiz.currentIndex + 1).toFloat() / quiz.questions.size.toFloat(),
                    fillColor = SkyBlue,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "${quiz.currentIndex + 1}/${quiz.questions.size}",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = "Translate into ${currentQ.targetLanguage.flag} ${currentQ.targetLanguage.englishName}",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            val hintKey = "quiz_hint_${currentQ.phrase.id}_${currentQ.targetLanguage.code}"
            val isSpeakingHint = uiState.speakingKey == hintKey

            TactileCard(
                onClick = { onSpeakQuizAudio(hintKey, currentQ.targetLanguage, currentQ.correctText) },
                containerColor = currentQ.targetLanguage.accentSoftColor.copy(alpha = 0.5f),
                borderColor = currentQ.targetLanguage.accentColor,
                lipColor = currentQ.targetLanguage.accentColor,
                cornerRadius = 20.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "${nativeLang.flag} ${nativeLang.englishName}",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = nativeText,
                            style = if (nativeText.length > 36) {
                                MaterialTheme.typography.headlineMedium
                            } else {
                                MaterialTheme.typography.headlineLarge
                            },
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Surface(
                        shape = CircleShape,
                        color = currentQ.targetLanguage.accentColor,
                        modifier = Modifier.size(44.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            if (isSpeakingHint) {
                                SoundWaveIndicator(isActive = true, color = Color.White)
                            } else {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Rounded.VolumeUp,
                                    contentDescription = "Hear hint in ${currentQ.targetLanguage.englishName}",
                                    tint = Color.White,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }
                    }
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                currentQ.options.forEachIndexed { optIndex, option ->
                    val isSelected = quiz.selectedOption == option
                    val isCorrectOption = option == currentQ.correctText
                    val optionAudioKey = "quiz_opt_${currentQ.phrase.id}_$optIndex"
                    val isSpeakingOption = uiState.speakingKey == optionAudioKey

                    val borderColor = when {
                        quiz.isAnswerChecked && isCorrectOption -> FeatherGreen
                        quiz.isAnswerChecked && isSelected && !quiz.isCorrect -> CoralRed
                        isSelected -> SkyBlue
                        else -> MaterialTheme.colorScheme.outline
                    }
                    val containerColor = when {
                        quiz.isAnswerChecked && isCorrectOption -> FeatherGreenSoft
                        quiz.isAnswerChecked && isSelected && !quiz.isCorrect -> CoralRedSoft
                        isSelected -> SkyBlueSoft
                        else -> MaterialTheme.colorScheme.surface
                    }

                    TactileCard(
                        onClick = { onSelectOption(option) },
                        containerColor = containerColor,
                        borderColor = borderColor,
                        lipColor = borderColor,
                        cornerRadius = 18.dp,
                        tag = "quiz_option_$optIndex"
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            // Dedicated play button so user can listen to each option
                            Surface(
                                onClick = {
                                    onSpeakQuizAudio(optionAudioKey, currentQ.targetLanguage, option)
                                },
                                shape = CircleShape,
                                color = if (isSpeakingOption) {
                                    currentQ.targetLanguage.accentColor
                                } else {
                                    currentQ.targetLanguage.accentSoftColor
                                },
                                border = BorderStroke(1.5.dp, currentQ.targetLanguage.accentColor),
                                modifier = Modifier
                                    .size(48.dp)
                                    .testTag("quiz_option_play_$optIndex")
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    if (isSpeakingOption) {
                                        SoundWaveIndicator(isActive = true, color = Color.White)
                                    } else {
                                        Icon(
                                            imageVector = Icons.Rounded.PlayArrow,
                                            contentDescription = "Listen to $option",
                                            tint = currentQ.targetLanguage.accentColor,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                }
                            }

                            Text(
                                text = option,
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.weight(1f)
                            )

                            if (quiz.isAnswerChecked && isCorrectOption) {
                                Icon(
                                    imageVector = Icons.Rounded.CheckCircle,
                                    contentDescription = "Correct",
                                    tint = FeatherGreenDark
                                )
                            }
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AnimatedVisibility(visible = quiz.isAnswerChecked) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = if (quiz.isCorrect) FeatherGreenSoft else CoralRedSoft,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (quiz.isCorrect) {
                            "Awesome! +15 XP · ${currentQ.correctText}"
                        } else {
                            "Correct answer: ${currentQ.correctText}"
                        },
                        style = MaterialTheme.typography.titleMedium,
                        color = if (quiz.isCorrect) FeatherGreenDark else CoralRedDark,
                        modifier = Modifier.padding(14.dp)
                    )
                }
            }

            if (!quiz.isAnswerChecked) {
                DuolingoButton(
                    text = "Check Answer",
                    enabled = quiz.selectedOption != null,
                    onClick = onCheckAnswer,
                    modifier = Modifier.fillMaxWidth(),
                    tag = "check_quiz_button"
                )
            } else {
                DuolingoButton(
                    text = if (quiz.currentIndex == quiz.questions.lastIndex) "See Results" else "Continue",
                    containerColor = if (quiz.isCorrect) FeatherGreen else CoralRed,
                    lipColor = if (quiz.isCorrect) FeatherGreenDark else CoralRedDark,
                    onClick = onNextQuestion,
                    modifier = Modifier.fillMaxWidth(),
                    tag = "continue_quiz_button"
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EditLanguagesDialog(
    draftTargets: List<String>,
    draftNative: String,
    limitNotice: String?,
    onToggleTarget: (String) -> Unit,
    onSelectNative: (String) -> Unit,
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Customize Languages",
                style = MaterialTheme.typography.headlineMedium
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Learning (${draftTargets.size}/3 max)",
                    style = MaterialTheme.typography.titleMedium
                )

                if (limitNotice != null) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = AmberGoldSoft
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Info,
                                contentDescription = null,
                                tint = AmberGold,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = limitNotice,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    LanguageCatalog.allLanguages.forEach { lang ->
                        val selected = draftTargets.contains(lang.code)
                        FilterChip(
                            selected = selected,
                            onClick = { onToggleTarget(lang.code) },
                            label = { Text("${lang.flag} ${lang.englishName}") },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = FeatherGreenSoft,
                                selectedLabelColor = FeatherGreenDark
                            )
                        )
                    }
                }

                Text(
                    text = "Native Language",
                    style = MaterialTheme.typography.titleMedium
                )

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    LanguageCatalog.nativePriorityOrder.forEach { lang ->
                        val isTarget = draftTargets.contains(lang.code)
                        val isSelected = draftNative == lang.code
                        FilterChip(
                            selected = isSelected,
                            enabled = !isTarget,
                            onClick = { onSelectNative(lang.code) },
                            label = { Text("${lang.flag} ${lang.englishName}") },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = SkyBlueSoft,
                                selectedLabelColor = SkyBlueDark
                            )
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onSave) {
                Text("Save", fontWeight = FontWeight.ExtraBold, color = FeatherGreenDark)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
