package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MenuBook
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.SlowMotionVideo
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.GreetingsLessonCatalog
import com.example.model.LessonAudioTerm
import com.example.model.LessonSection
import com.example.model.LessonTableRow
import com.example.model.SupportedLanguage
import com.example.ui.PolyglotUiState
import com.example.ui.components.SoundWaveIndicator
import com.example.ui.components.TactileCard
import com.example.ui.theme.AmberGold
import com.example.ui.theme.AmberGoldSoft

@Composable
fun LessonGuideScreen(
    uiState: PolyglotUiState,
    selectedLanguageCode: String,
    onSelectLanguageCode: (String) -> Unit,
    onToggleSlowNarration: () -> Unit,
    onSpeakLessonWord: (key: String, language: SupportedLanguage, text: String) -> Unit
) {
    val targets = uiState.targetLanguages
    val activeLang = targets.find { it.code == selectedLanguageCode } ?: targets.first()
    val guide = GreetingsLessonCatalog.guideFor(activeLang.code)

    LazyColumn(
        modifier = Modifier
            .widthIn(max = 680.dp)
            .fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Header + Language Switcher + Slow Audio Toggle
        item(key = "lesson_header") {
            Surface(
                shape = RoundedCornerShape(22.dp),
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(2.dp, activeLang.accentColor.copy(alpha = 0.65f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    activeLang.accentSoftColor.copy(alpha = 0.55f),
                                    MaterialTheme.colorScheme.surface
                                )
                            )
                        )
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = activeLang.accentColor
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.MenuBook,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.padding(8.dp).size(20.dp)
                                )
                            }
                            Column {
                                Text(
                                    text = "GREETINGS LESSON • ${guide.levelBadge.uppercase()}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = activeLang.accentColor
                                )
                                Text(
                                    text = "${activeLang.flag} ${activeLang.englishName}: ${guide.topicTitle}",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }

                    Text(
                        text = guide.summary,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    // Switch between the user's selected languages
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(targets, key = { it.code }) { lang ->
                            val isSelected = lang.code == activeLang.code
                            Surface(
                                onClick = { onSelectLanguageCode(lang.code) },
                                shape = RoundedCornerShape(999.dp),
                                color = if (isSelected) lang.accentColor else MaterialTheme.colorScheme.background,
                                border = BorderStroke(
                                    1.5.dp,
                                    if (isSelected) lang.accentColor else MaterialTheme.colorScheme.outline
                                ),
                                modifier = Modifier
                                    .minimumInteractiveComponentSize()
                                    .testTag("lesson_lang_${lang.code}")
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Text(text = lang.flag, fontSize = 16.sp)
                                    Text(
                                        text = lang.englishName,
                                        style = MaterialTheme.typography.labelLarge,
                                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }

                    // Slow Narration toggle inside Lesson Guide
                    Surface(
                        onClick = onToggleSlowNarration,
                        shape = RoundedCornerShape(14.dp),
                        color = if (uiState.slowNarrationEnabled) AmberGoldSoft else MaterialTheme.colorScheme.background,
                        border = BorderStroke(
                            1.5.dp,
                            if (uiState.slowNarrationEnabled) AmberGold else MaterialTheme.colorScheme.outline
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .minimumInteractiveComponentSize()
                            .testTag("lesson_slow_toggle")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 9.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.SlowMotionVideo,
                                contentDescription = "Toggle slow audio in lesson",
                                tint = if (uiState.slowNarrationEnabled) AmberGold else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (uiState.slowNarrationEnabled) {
                                    "Slow Narration: ON (0.7x) — tap any highlighted word to hear"
                                } else {
                                    "Tap any highlighted word below to hear · Slow Audio: OFF"
                                },
                                style = MaterialTheme.typography.labelMedium,
                                color = if (uiState.slowNarrationEnabled) AmberGold else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        // Numbered Lesson Sections
        items(guide.sections, key = { "${activeLang.code}_sec_${it.number}" }) { section ->
            LessonSectionCard(
                section = section,
                language = activeLang,
                speakingKey = uiState.speakingKey,
                onSpeakWord = { key, text -> onSpeakLessonWord(key, activeLang, text) }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun LessonSectionCard(
    section: LessonSection,
    language: SupportedLanguage,
    speakingKey: String?,
    onSpeakWord: (key: String, text: String) -> Unit
) {
    TactileCard(
        containerColor = MaterialTheme.colorScheme.surface,
        borderColor = MaterialTheme.colorScheme.outline,
        lipColor = MaterialTheme.colorScheme.outline,
        cornerRadius = 20.dp,
        tag = "lesson_section_${section.number}"
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(language.accentColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${section.number}",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.White
                    )
                }
                Text(
                    text = section.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
            }

            section.paragraphs.forEachIndexed { pIndex, paragraph ->
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    val annotated = buildAnnotatedString {
                        paragraph.segments.forEach { segment ->
                            if (segment.isTargetHighlight) {
                                withStyle(
                                    SpanStyle(
                                        background = language.accentSoftColor,
                                        color = language.accentColor,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                ) {
                                    append(" ${segment.text} ")
                                }
                            } else {
                                append(segment.text)
                            }
                        }
                    }

                    Text(
                        text = annotated,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    if (paragraph.audioTerms.isNotEmpty()) {
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            paragraph.audioTerms.forEachIndexed { tIndex, term ->
                                val key = "lesson_${language.code}_${section.number}_${pIndex}_$tIndex"
                                HighlightedPlayableWordChip(
                                    term = term,
                                    language = language,
                                    isSpeaking = speakingKey == key,
                                    onPlay = { onSpeakWord(key, term.spokenText) },
                                    tag = key
                                )
                            }
                        }
                    }
                }
            }

            if (section.tableRows.isNotEmpty()) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    section.tableRows.forEachIndexed { rowIdx, row ->
                        LessonPronounRowCard(
                            row = row,
                            language = language,
                            speakingKey = speakingKey,
                            rowIndex = rowIdx,
                            sectionNumber = section.number,
                            onSpeakWord = onSpeakWord
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HighlightedPlayableWordChip(
    term: LessonAudioTerm,
    language: SupportedLanguage,
    isSpeaking: Boolean,
    onPlay: () -> Unit,
    tag: String
) {
    Surface(
        onClick = onPlay,
        shape = RoundedCornerShape(16.dp),
        color = if (isSpeaking) language.accentColor else language.accentSoftColor,
        border = BorderStroke(1.5.dp, language.accentColor),
        modifier = Modifier
            .minimumInteractiveComponentSize()
            .testTag(tag)
    ) {
        Row(
            modifier = Modifier.padding(start = 12.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Column(modifier = Modifier.weight(1f, fill = false)) {
                Text(
                    text = term.displayText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = if (isSpeaking) Color.White else language.accentColor
                )
                if (term.meaning.isNotBlank()) {
                    Text(
                        text = term.meaning,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (isSpeaking) Color.White.copy(alpha = 0.9f) else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Surface(
                shape = CircleShape,
                color = if (isSpeaking) Color.White.copy(alpha = 0.25f) else language.accentColor,
                modifier = Modifier.size(34.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    if (isSpeaking) {
                        SoundWaveIndicator(isActive = true, color = Color.White)
                    } else {
                        Icon(
                            imageVector = Icons.Rounded.PlayArrow,
                            contentDescription = "Hear ${term.displayText}",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LessonPronounRowCard(
    row: LessonTableRow,
    language: SupportedLanguage,
    speakingKey: String?,
    sectionNumber: Int,
    rowIndex: Int,
    onSpeakWord: (key: String, text: String) -> Unit
) {
    val termKey = "tbl_term_${language.code}_${sectionNumber}_$rowIndex"
    val exampleKey = "tbl_ex_${language.code}_${sectionNumber}_$rowIndex"

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.background,
        border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Highlighted Pronoun + Play Button
                Surface(
                    onClick = { onSpeakWord(termKey, row.termSpoken) },
                    shape = RoundedCornerShape(12.dp),
                    color = if (speakingKey == termKey) language.accentColor else language.accentSoftColor,
                    border = BorderStroke(1.5.dp, language.accentColor),
                    modifier = Modifier.minimumInteractiveComponentSize()
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = row.term,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = if (speakingKey == termKey) Color.White else language.accentColor
                        )
                        if (speakingKey == termKey) {
                            SoundWaveIndicator(isActive = true, color = Color.White)
                        } else {
                            Icon(
                                imageVector = Icons.Rounded.PlayArrow,
                                contentDescription = "Hear ${row.term}",
                                tint = language.accentColor,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = row.literalMeaning,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f)
                )
            }

            Text(
                text = row.usageContext,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.6f))

            // Highlighted Example + Play Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onSpeakWord(exampleKey, row.exampleSpoken) }
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = row.exampleText,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = language.accentColor
                    )
                    Text(
                        text = row.exampleMeaning,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Surface(
                    onClick = { onSpeakWord(exampleKey, row.exampleSpoken) },
                    shape = CircleShape,
                    color = if (speakingKey == exampleKey) language.accentColor else language.accentSoftColor,
                    border = BorderStroke(1.5.dp, language.accentColor),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        if (speakingKey == exampleKey) {
                            SoundWaveIndicator(isActive = true, color = Color.White)
                        } else {
                            Icon(
                                imageVector = Icons.Rounded.PlayArrow,
                                contentDescription = "Hear ${row.exampleText}",
                                tint = language.accentColor,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
