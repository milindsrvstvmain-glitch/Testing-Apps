package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.LanguageCatalog
import com.example.model.SupportedLanguage
import com.example.ui.components.DuolingoButton
import com.example.ui.components.OnboardingHeader
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
fun WelcomeScreen(
    username: String,
    onUsernameChange: (String) -> Unit,
    onContinue: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val isNameValid = username.trim().isNotEmpty()

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .imePadding(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 540.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                OnboardingHeader(step = 1, totalSteps = 3)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Playful mascot badge
                    Surface(
                        shape = RoundedCornerShape(32.dp),
                        color = FeatherGreenSoft,
                        border = BorderStroke(3.dp, FeatherGreen.copy(alpha = 0.35f)),
                        modifier = Modifier.size(164.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_mascot_welcome),
                            contentDescription = "Polyglot Master mascot waving",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(30.dp))
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Surface(
                        shape = RoundedCornerShape(999.dp),
                        color = SkyBlueSoft
                    ) {
                        Text(
                            text = "POLYGLOT MASTER",
                            style = MaterialTheme.typography.labelMedium,
                            color = SkyBlueDark,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = stringResource(R.string.welcome_title),
                        style = MaterialTheme.typography.displayMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = stringResource(R.string.welcome_subtitle),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    TactileCard(
                        modifier = Modifier.fillMaxWidth(),
                        cornerRadius = 22.dp
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = "First, what should we call you?",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            OutlinedTextField(
                                value = username,
                                onValueChange = onUsernameChange,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("username_input"),
                                singleLine = true,
                                placeholder = {
                                    Text(stringResource(R.string.username_placeholder))
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Rounded.Person,
                                        contentDescription = null,
                                        tint = FeatherGreen
                                    )
                                },
                                shape = RoundedCornerShape(16.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = FeatherGreen,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                                    focusedContainerColor = MaterialTheme.colorScheme.background,
                                    unfocusedContainerColor = MaterialTheme.colorScheme.background
                                ),
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.Words,
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        keyboardController?.hide()
                                        if (isNameValid) onContinue()
                                    }
                                )
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 20.dp)
                        .navigationBarsPadding()
                ) {
                    DuolingoButton(
                        text = stringResource(R.string.continue_cta),
                        enabled = isNameValid,
                        onClick = {
                            keyboardController?.hide()
                            onContinue()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        tag = "welcome_continue_button"
                    )
                }
            }
        }
    }
}

@Composable
fun SelectLanguagesScreen(
    username: String,
    selectedLanguageCodes: List<String>,
    limitNotice: String?,
    onToggleLanguage: (String) -> Unit,
    onBack: () -> Unit,
    onContinue: () -> Unit
) {
    val cleanName = username.trim().ifEmpty { "Friend" }

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            Surface(
                color = MaterialTheme.colorScheme.background,
                shadowElevation = 6.dp
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(horizontal = 24.dp, vertical = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    val count = selectedLanguageCodes.size
                    val ctaText = if (count == 0) {
                        "Pick at least 1 language"
                    } else {
                        "Continue ($count/3 selected)"
                    }
                    DuolingoButton(
                        text = ctaText,
                        enabled = count in 1..3,
                        onClick = onContinue,
                        modifier = Modifier
                            .widthIn(max = 540.dp)
                            .fillMaxWidth(),
                        tag = "languages_continue_button"
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 640.dp)
                    .fillMaxSize()
            ) {
                OnboardingHeader(step = 2, totalSteps = 3, onBack = onBack)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Hi $cleanName!",
                            style = MaterialTheme.typography.titleMedium,
                            color = FeatherGreenDark
                        )
                        Surface(
                            shape = RoundedCornerShape(999.dp),
                            color = if (selectedLanguageCodes.size == 3) FeatherGreenSoft else SkyBlueSoft
                        ) {
                            Text(
                                text = "${selectedLanguageCodes.size} / 3 max",
                                style = MaterialTheme.typography.labelMedium,
                                color = if (selectedLanguageCodes.size == 3) FeatherGreenDark else SkyBlueDark,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                            )
                        }
                    }

                    Text(
                        text = stringResource(R.string.select_languages_title),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Text(
                        text = stringResource(R.string.select_languages_subtitle),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    // Selected summary pills
                    AnimatedVisibility(visible = selectedLanguageCodes.isNotEmpty()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            selectedLanguageCodes.forEach { code ->
                                val lang = LanguageCatalog.findByCode(code)
                                Surface(
                                    onClick = { onToggleLanguage(code) },
                                    shape = RoundedCornerShape(999.dp),
                                    color = lang.accentSoftColor,
                                    border = BorderStroke(1.5.dp, lang.accentColor)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Text(text = lang.flag, fontSize = 14.sp)
                                        Text(
                                            text = lang.englishName,
                                            style = MaterialTheme.typography.labelMedium,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Icon(
                                            imageVector = Icons.Rounded.Close,
                                            contentDescription = "Remove ${lang.englishName}",
                                            modifier = Modifier.size(14.dp),
                                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }
                    }

                    AnimatedVisibility(visible = limitNotice != null) {
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = AmberGoldSoft,
                            border = BorderStroke(1.dp, AmberGold),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Info,
                                    contentDescription = null,
                                    tint = AmberGold,
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = limitNotice.orEmpty(),
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                BoxWithConstraints(modifier = Modifier.weight(1f)) {
                    val columns = if (maxWidth >= 560.dp) 3 else 2
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(columns),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(LanguageCatalog.allLanguages, key = { it.code }) { language ->
                            val isSelected = selectedLanguageCodes.contains(language.code)
                            val selectionOrder = selectedLanguageCodes.indexOf(language.code) + 1
                            LanguageChoiceCard(
                                language = language,
                                isSelected = isSelected,
                                selectionOrder = selectionOrder,
                                onClick = { onToggleLanguage(language.code) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LanguageChoiceCard(
    language: SupportedLanguage,
    isSelected: Boolean,
    selectionOrder: Int,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) FeatherGreen else MaterialTheme.colorScheme.outline
    val lipColor = if (isSelected) FeatherGreenDark else MaterialTheme.colorScheme.outline
    val containerColor = if (isSelected) FeatherGreenSoft.copy(alpha = 0.45f) else MaterialTheme.colorScheme.surface

    TactileCard(
        onClick = onClick,
        containerColor = containerColor,
        borderColor = borderColor,
        lipColor = lipColor,
        cornerRadius = 18.dp,
        tag = "lang_option_${language.code}"
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = language.flag, fontSize = 28.sp)
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(FeatherGreen),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$selectionOrder",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }

            Text(
                text = language.englishName,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = "${language.nativeName} · \"${language.sampleHello}\"",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun NativeLanguageScreen(
    selectedNativeCode: String,
    selectedTargetCodes: List<String>,
    onSelectNative: (String) -> Unit,
    onBack: () -> Unit,
    onFinish: () -> Unit
) {
    val availableNativeLanguages = LanguageCatalog.nativePriorityOrder

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            Surface(
                color = MaterialTheme.colorScheme.background,
                shadowElevation = 6.dp
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(horizontal = 24.dp, vertical = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    DuolingoButton(
                        text = stringResource(R.string.start_learning_cta),
                        containerColor = SkyBlue,
                        lipColor = SkyBlueDark,
                        onClick = onFinish,
                        modifier = Modifier
                            .widthIn(max = 540.dp)
                            .fillMaxWidth(),
                        tag = "start_learning_button"
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 580.dp)
                    .fillMaxSize()
            ) {
                OnboardingHeader(step = 3, totalSteps = 3, onBack = onBack)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = stringResource(R.string.native_language_title),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = stringResource(R.string.native_language_subtitle),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(availableNativeLanguages, key = { it.code }) { language ->
                        val isTarget = selectedTargetCodes.contains(language.code)
                        val isSelected = selectedNativeCode == language.code

                        TactileCard(
                            onClick = if (!isTarget) {
                                { onSelectNative(language.code) }
                            } else {
                                null
                            },
                            containerColor = when {
                                isSelected -> SkyBlueSoft.copy(alpha = 0.5f)
                                isTarget -> MaterialTheme.colorScheme.background
                                else -> MaterialTheme.colorScheme.surface
                            },
                            borderColor = if (isSelected) SkyBlue else MaterialTheme.colorScheme.outline,
                            lipColor = if (isSelected) SkyBlueDark else MaterialTheme.colorScheme.outline,
                            cornerRadius = 18.dp,
                            tag = "native_lang_${language.code}"
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                                ) {
                                    Text(text = language.flag, fontSize = 28.sp)
                                    Column {
                                        Text(
                                            text = language.englishName,
                                            style = MaterialTheme.typography.titleMedium,
                                            color = if (isTarget) {
                                                MaterialTheme.colorScheme.onSurfaceVariant
                                            } else {
                                                MaterialTheme.colorScheme.onSurface
                                            }
                                        )
                                        Text(
                                            text = language.nativeName,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }

                                if (isTarget) {
                                    Surface(
                                        shape = RoundedCornerShape(999.dp),
                                        color = FeatherGreenSoft
                                    ) {
                                        Text(
                                            text = "Learning",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = FeatherGreenDark,
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                        )
                                    }
                                } else if (isSelected) {
                                    Icon(
                                        imageVector = Icons.Rounded.CheckCircle,
                                        contentDescription = "Selected native language",
                                        tint = SkyBlue,
                                        modifier = Modifier.size(26.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
