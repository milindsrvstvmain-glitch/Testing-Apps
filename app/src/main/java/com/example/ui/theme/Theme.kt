package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val LightColorScheme = lightColorScheme(
    primary = FeatherGreen,
    onPrimary = Color.White,
    primaryContainer = FeatherGreenSoft,
    onPrimaryContainer = FeatherGreenDark,
    secondary = SkyBlue,
    onSecondary = Color.White,
    secondaryContainer = SkyBlueSoft,
    onSecondaryContainer = SkyBlueDark,
    tertiary = AmberGold,
    onTertiary = Color.White,
    tertiaryContainer = AmberGoldSoft,
    onTertiaryContainer = AmberGoldDark,
    error = CoralRed,
    onError = Color.White,
    errorContainer = CoralRedSoft,
    onErrorContainer = CoralRedDark,
    background = SurfaceLight,
    onBackground = TextPrimaryLight,
    surface = CardLight,
    onSurface = TextPrimaryLight,
    surfaceVariant = SurfaceLight,
    onSurfaceVariant = TextSecondaryLight,
    outline = BorderLight,
    outlineVariant = BorderLight
)

private val DarkColorScheme = darkColorScheme(
    primary = FeatherGreen,
    onPrimary = Color(0xFF0A2200),
    primaryContainer = Color(0xFF1F420B),
    onPrimaryContainer = FeatherGreenSoft,
    secondary = SkyBlue,
    onSecondary = Color(0xFF002438),
    secondaryContainer = Color(0xFF103A50),
    onSecondaryContainer = SkyBlueSoft,
    tertiary = AmberGold,
    onTertiary = Color(0xFF2F1900),
    tertiaryContainer = Color(0xFF472C00),
    onTertiaryContainer = AmberGoldSoft,
    error = CoralRed,
    onError = Color.White,
    errorContainer = Color(0xFF4D1515),
    onErrorContainer = CoralRedSoft,
    background = SurfaceDark,
    onBackground = TextPrimaryDark,
    surface = CardDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = CardDark,
    onSurfaceVariant = TextSecondaryDark,
    outline = BorderDark,
    outlineVariant = BorderDark
)

val AppShapes = Shapes(
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(18.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

@Composable
fun PolyglotMasterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
