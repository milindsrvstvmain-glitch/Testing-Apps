package com.example.model

data class TextSegment(
    val text: String,
    val isTargetHighlight: Boolean = false
)

data class LessonAudioTerm(
    val spokenText: String,
    val displayText: String,
    val meaning: String = ""
)

data class LessonTableRow(
    val term: String,
    val termSpoken: String,
    val literalMeaning: String,
    val usageContext: String,
    val exampleText: String,
    val exampleSpoken: String,
    val exampleMeaning: String
)

data class LessonParagraph(
    val segments: List<TextSegment>,
    val audioTerms: List<LessonAudioTerm> = emptyList()
)

data class LessonSection(
    val number: Int,
    val title: String,
    val paragraphs: List<LessonParagraph>,
    val tableRows: List<LessonTableRow> = emptyList()
)

data class LanguageLessonGuide(
    val languageCode: String,
    val topicTitle: String,
    val levelBadge: String,
    val summary: String,
    val sections: List<LessonSection>
)
