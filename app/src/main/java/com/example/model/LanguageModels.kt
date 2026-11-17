package com.example.model

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.AmberGold
import com.example.ui.theme.AmberGoldSoft
import com.example.ui.theme.CoralRed
import com.example.ui.theme.CoralRedSoft
import com.example.ui.theme.FeatherGreen
import com.example.ui.theme.FeatherGreenSoft
import com.example.ui.theme.RoyalPurple
import com.example.ui.theme.RoyalPurpleSoft
import com.example.ui.theme.SkyBlue
import com.example.ui.theme.SkyBlueSoft
import com.example.ui.theme.TealAccent
import com.example.ui.theme.TealAccentSoft

data class SupportedLanguage(
    val code: String,
    val englishName: String,
    val nativeName: String,
    val flag: String,
    val localeTag: String,
    val sampleHello: String,
    val accentColor: Color,
    val accentSoftColor: Color
)

object LanguageCatalog {
    val allLanguages: List<SupportedLanguage> = listOf(
        SupportedLanguage(
            code = "fr",
            englishName = "French",
            nativeName = "Français",
            flag = "🇫🇷",
            localeTag = "fr-FR",
            sampleHello = "Bonjour",
            accentColor = SkyBlue,
            accentSoftColor = SkyBlueSoft
        ),
        SupportedLanguage(
            code = "es",
            englishName = "Spanish",
            nativeName = "Español",
            flag = "🇪🇸",
            localeTag = "es-ES",
            sampleHello = "Hola",
            accentColor = AmberGold,
            accentSoftColor = AmberGoldSoft
        ),
        SupportedLanguage(
            code = "vi",
            englishName = "Vietnamese",
            nativeName = "Tiếng Việt",
            flag = "🇻🇳",
            localeTag = "vi-VN",
            sampleHello = "Xin chào",
            accentColor = CoralRed,
            accentSoftColor = CoralRedSoft
        ),
        SupportedLanguage(
            code = "en",
            englishName = "English",
            nativeName = "English",
            flag = "🇺🇸",
            localeTag = "en-US",
            sampleHello = "Hello",
            accentColor = FeatherGreen,
            accentSoftColor = FeatherGreenSoft
        ),
        SupportedLanguage(
            code = "ja",
            englishName = "Japanese",
            nativeName = "日本語",
            flag = "🇯🇵",
            localeTag = "ja-JP",
            sampleHello = "こんにちは",
            accentColor = RoyalPurple,
            accentSoftColor = RoyalPurpleSoft
        ),
        SupportedLanguage(
            code = "de",
            englishName = "German",
            nativeName = "Deutsch",
            flag = "🇩🇪",
            localeTag = "de-DE",
            sampleHello = "Hallo",
            accentColor = TealAccent,
            accentSoftColor = TealAccentSoft
        ),
        SupportedLanguage(
            code = "it",
            englishName = "Italian",
            nativeName = "Italiano",
            flag = "🇮🇹",
            localeTag = "it-IT",
            sampleHello = "Ciao",
            accentColor = FeatherGreen,
            accentSoftColor = FeatherGreenSoft
        ),
        SupportedLanguage(
            code = "ko",
            englishName = "Korean",
            nativeName = "한국어",
            flag = "🇰🇷",
            localeTag = "ko-KR",
            sampleHello = "안녕하세요",
            accentColor = SkyBlue,
            accentSoftColor = SkyBlueSoft
        ),
        SupportedLanguage(
            code = "pt",
            englishName = "Portuguese",
            nativeName = "Português",
            flag = "🇧🇷",
            localeTag = "pt-BR",
            sampleHello = "Olá",
            accentColor = AmberGold,
            accentSoftColor = AmberGoldSoft
        ),
        SupportedLanguage(
            code = "zh",
            englishName = "Mandarin",
            nativeName = "中文",
            flag = "🇨🇳",
            localeTag = "zh-CN",
            sampleHello = "你好",
            accentColor = CoralRed,
            accentSoftColor = CoralRedSoft
        ),
        SupportedLanguage(
            code = "hi",
            englishName = "Hindi",
            nativeName = "हिन्दी",
            flag = "🇮🇳",
            localeTag = "hi-IN",
            sampleHello = "नमस्ते",
            accentColor = RoyalPurple,
            accentSoftColor = RoyalPurpleSoft
        ),
        SupportedLanguage(
            code = "ar",
            englishName = "Arabic",
            nativeName = "العربية",
            flag = "🇸🇦",
            localeTag = "ar-SA",
            sampleHello = "مرحبا",
            accentColor = TealAccent,
            accentSoftColor = TealAccentSoft
        ),
        SupportedLanguage(
            code = "th",
            englishName = "Thai",
            nativeName = "ภาษาไทย",
            flag = "🇹🇭",
            localeTag = "th-TH",
            sampleHello = "สวัสดี",
            accentColor = RoyalPurple,
            accentSoftColor = RoyalPurpleSoft
        )
    )

    val nativePriorityOrder: List<SupportedLanguage> =
        listOf("en", "es", "fr", "vi", "th", "hi", "pt", "de", "ja", "ko", "zh", "it", "ar")
            .mapNotNull { code -> allLanguages.find { it.code == code } }

    fun findByCode(code: String): SupportedLanguage =
        allLanguages.find { it.code == code } ?: allLanguages.first { it.code == "en" }
}

data class PhraseTranslation(
    val text: String,
    val phonetic: String,
    val usageNote: String
) {
    fun formattedText(username: String): String {
        val cleanName = username.trim().ifEmpty { "Alex" }
        return text.replace("{name}", cleanName)
    }

    fun formattedPhonetic(username: String): String {
        val cleanName = username.trim().ifEmpty { "Alex" }
        return phonetic.replace("{name}", cleanName)
    }
}

data class LearningPhrase(
    val id: String,
    val shortTitle: String,
    val translations: Map<String, PhraseTranslation>,
    val speakerRole: String? = null
) {
    fun translationFor(langCode: String): PhraseTranslation =
        translations[langCode] ?: translations["en"]
        ?: PhraseTranslation(shortTitle, "", "")
}

data class LessonUnit(
    val id: String,
    val number: Int,
    val title: String,
    val subtitle: String,
    val emoji: String,
    val phrases: List<LearningPhrase>
)

data class UserProfile(
    val username: String = "",
    val nativeLanguageCode: String = "en",
    val targetLanguageCodes: List<String> = emptyList(),
    val xp: Int = 0,
    val streakDays: Int = 1,
    val heardPhraseKeys: Set<String> = emptySet(),
    val completedQuizUnitIds: Set<String> = emptySet(),
    val onboardingCompleted: Boolean = false
)
