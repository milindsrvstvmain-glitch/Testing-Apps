package com.example.data

import com.example.model.LanguageLessonGuide
import com.example.model.LessonParagraph
import com.example.model.LessonSection

object MoreGreetingsGuides {

    fun guideFor(languageCode: String): LanguageLessonGuide = when (languageCode) {
        "es" -> spanishGuide()
        "ja" -> japaneseGuide()
        "de" -> germanGuide()
        "it" -> italianGuide()
        "ko" -> koreanGuide()
        "pt" -> portugueseGuide()
        "zh" -> mandarinGuide()
        "hi" -> hindiGuide()
        "ar" -> arabicGuide()
        else -> englishGuide()
    }

    private fun spanishGuide() = LanguageLessonGuide(
        languageCode = "es",
        topicTitle = "Greetings & Politeness",
        levelBadge = "A1 Foundation",
        summary = "Hola, time-of-day greetings, tú vs. usted, and warm farewells.",
        sections = listOf(
            LessonSection(
                number = 1,
                title = "The Universal Greeting: Hola",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            hl("Hola"),
                            plain(" works everywhere—casual or formal, morning or night. Remember that the \"h\" is always silent (pronounced OH-lah).")
                        ),
                        audioTerms = listOf(audio("Hola", "Hola", "Universal Hello (OH-lah)"))
                    )
                )
            ),
            LessonSection(
                number = 2,
                title = "Time-Specific Greetings",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("In shops, offices, and polite conversation, switch greetings with the time of day: "),
                            hl("Buenos días"),
                            plain(" (morning), "),
                            hl("Buenas tardes"),
                            plain(" (afternoon), and "),
                            hl("Buenas noches"),
                            plain(" (evening / goodnight).")
                        ),
                        audioTerms = listOf(
                            audio("Buenos días", "Buenos días", "Good morning (until noon)"),
                            audio("Buenas tardes", "Buenas tardes", "Good afternoon (until sunset)"),
                            audio("Buenas noches", "Buenas noches", "Good evening / Goodnight")
                        )
                    )
                )
            ),
            LessonSection(
                number = 3,
                title = "Polite vs. Casual Check-In",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("Use "),
                            hl("¿Cómo estás?"),
                            plain(" with friends and peers, or "),
                            hl("¿Cómo está usted?"),
                            plain(" with elders and formal contacts.")
                        ),
                        audioTerms = listOf(
                            audio("¿Cómo estás?", "¿Cómo estás?", "How are you? (friendly)"),
                            audio("¿Cómo está usted?", "¿Cómo está usted?", "How are you? (formal)"),
                            audio("Muy bien, gracias", "Muy bien, gracias", "Very well, thank you")
                        )
                    )
                )
            ),
            LessonSection(
                number = 4,
                title = "Saying Goodbye",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("Common farewells include "),
                            hl("Adiós"),
                            plain(" (Goodbye) and "),
                            hl("Hasta luego"),
                            plain(" (See you later).")
                        ),
                        audioTerms = listOf(
                            audio("Adiós", "Adiós", "Goodbye"),
                            audio("Hasta luego", "Hasta luego", "See you later"),
                            audio("Hasta mañana", "Hasta mañana", "See you tomorrow")
                        )
                    )
                )
            )
        )
    )

    private fun japaneseGuide() = LanguageLessonGuide(
        languageCode = "ja",
        topicTitle = "Greetings & Politeness",
        levelBadge = "A1 Foundation",
        summary = "Time-based greetings, bowing etiquette, and polite self-introductions.",
        sections = listOf(
            LessonSection(
                number = 1,
                title = "Time-of-Day Greetings",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("Japanese relies heavily on the time of day: "),
                            hl("おはようございます"),
                            plain(" in the morning, "),
                            hl("こんにちは"),
                            plain(" during the daytime, and "),
                            hl("こんばんは"),
                            plain(" in the evening.")
                        ),
                        audioTerms = listOf(
                            audio("おはようございます", "おはようございます (Ohayō gozaimasu)", "Good morning (polite)"),
                            audio("こんにちは", "こんにちは (Konnichiwa)", "Hello / Good afternoon"),
                            audio("こんばんは", "こんばんは (Konbanwa)", "Good evening")
                        )
                    )
                )
            ),
            LessonSection(
                number = 2,
                title = "First Meetings & Farewells",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("When meeting someone for the very first time, say "),
                            hl("はじめまして"),
                            plain(" with a gentle bow, and when parting casually, say "),
                            hl("またね"),
                            plain(" or formally "),
                            hl("さようなら"),
                            plain(".")
                        ),
                        audioTerms = listOf(
                            audio("はじめまして", "はじめまして (Hajimemashite)", "Nice to meet you (first time)"),
                            audio("お元気ですか？", "お元気ですか？ (Ogenki desu ka?)", "How are you?"),
                            audio("さようなら", "さようなら (Sayōnara)", "Goodbye (formal)")
                        )
                    )
                )
            )
        )
    )

    private fun germanGuide() = LanguageLessonGuide(
        languageCode = "de",
        topicTitle = "Greetings & Politeness",
        levelBadge = "A1 Foundation",
        summary = "Hallo, regional greetings, Sie vs. du, and polite farewells.",
        sections = listOf(
            LessonSection(
                number = 1,
                title = "Everyday & Time-Based Greetings",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            hl("Hallo"),
                            plain(" works almost everywhere casually, while "),
                            hl("Guten Morgen"),
                            plain(", "),
                            hl("Guten Tag"),
                            plain(", and "),
                            hl("Guten Abend"),
                            plain(" are preferred in formal and business settings.")
                        ),
                        audioTerms = listOf(
                            audio("Hallo", "Hallo", "Hello (standard friendly)"),
                            audio("Guten Morgen", "Guten Morgen", "Good morning"),
                            audio("Guten Tag", "Guten Tag", "Good day (polite)"),
                            audio("Guten Abend", "Guten Abend", "Good evening")
                        )
                    )
                )
            ),
            LessonSection(
                number = 2,
                title = "Formal vs. Casual & Goodbyes",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("Ask "),
                            hl("Wie geht es Ihnen?"),
                            plain(" politely, or "),
                            hl("Wie geht's?"),
                            plain(" with friends. Say "),
                            hl("Auf Wiedersehen"),
                            plain(" formally or "),
                            hl("Tschüss"),
                            plain(" casually.")
                        ),
                        audioTerms = listOf(
                            audio("Wie geht es Ihnen?", "Wie geht es Ihnen?", "How are you? (formal)"),
                            audio("Auf Wiedersehen", "Auf Wiedersehen", "Goodbye (formal)"),
                            audio("Tschüss!", "Tschüss!", "Bye! (casual)")
                        )
                    )
                )
            )
        )
    )

    private fun italianGuide() = LanguageLessonGuide(
        languageCode = "it",
        topicTitle = "Greetings & Politeness",
        levelBadge = "A1 Foundation",
        summary = "Ciao vs. Buongiorno, polite check-ins, and warm goodbyes.",
        sections = listOf(
            LessonSection(
                number = 1,
                title = "Ciao vs. Buongiorno",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("Use "),
                            hl("Ciao"),
                            plain(" with friends and family (for both hello and bye), and "),
                            hl("Buongiorno"),
                            plain(" or "),
                            hl("Buonasera"),
                            plain(" when entering shops or greeting strangers politely.")
                        ),
                        audioTerms = listOf(
                            audio("Ciao", "Ciao", "Hi / Bye (informal)"),
                            audio("Buongiorno", "Buongiorno", "Good morning / day (polite)"),
                            audio("Buonasera", "Buonasera", "Good evening (polite)"),
                            audio("Arrivederci", "Arrivederci", "Goodbye (polite)")
                        )
                    )
                )
            )
        )
    )

    private fun koreanGuide() = LanguageLessonGuide(
        languageCode = "ko",
        topicTitle = "Greetings & Politeness",
        levelBadge = "A1 Foundation",
        summary = "Polite speech levels (-yo / -mnida), bowing, and natural goodbyes.",
        sections = listOf(
            LessonSection(
                number = 1,
                title = "Polite vs. Casual Hello",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            hl("안녕하세요"),
                            plain(" is your essential polite greeting for almost everyone. With close friends or younger kids, drop the ending to "),
                            hl("안녕"),
                            plain(".")
                        ),
                        audioTerms = listOf(
                            audio("안녕하세요", "안녕하세요 (Annyeonghaseyo)", "Polite everyday hello"),
                            audio("안녕", "안녕 (Annyeong)", "Casual Hi / Bye with close friends"),
                            audio("만나서 반갑습니다", "만나서 반갑습니다", "Nice to meet you")
                        )
                    )
                )
            ),
            LessonSection(
                number = 2,
                title = "Two Ways to Say Goodbye",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("Say "),
                            hl("안녕히 가세요"),
                            plain(" to someone leaving, and "),
                            hl("안녕히 계세요"),
                            plain(" when you are the one leaving.")
                        ),
                        audioTerms = listOf(
                            audio("안녕히 가세요", "안녕히 가세요", "Goodbye (to someone leaving)"),
                            audio("안녕히 계세요", "안녕히 계세요", "Goodbye (when you leave)")
                        )
                    )
                )
            )
        )
    )

    private fun portugueseGuide() = LanguageLessonGuide(
        languageCode = "pt",
        topicTitle = "Greetings & Politeness",
        levelBadge = "A1 Foundation",
        summary = "Olá, Oi, Tudo bem?, and warm Portuguese & Brazilian farewells.",
        sections = listOf(
            LessonSection(
                number = 1,
                title = "Olá, Oi & Tudo bem?",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("Start with "),
                            hl("Olá"),
                            plain(" (universal) or "),
                            hl("Oi"),
                            plain(" (friendly), then immediately ask "),
                            hl("Tudo bem?"),
                            plain(" (All good?).")
                        ),
                        audioTerms = listOf(
                            audio("Olá", "Olá", "Universal Hello"),
                            audio("Oi!", "Oi!", "Friendly Hi"),
                            audio("Tudo bem?", "Tudo bem?", "How are you? / All good?"),
                            audio("Tchau", "Tchau", "Goodbye")
                        )
                    )
                )
            )
        )
    )

    private fun mandarinGuide() = LanguageLessonGuide(
        languageCode = "zh",
        topicTitle = "Greetings & Politeness",
        levelBadge = "A1 Foundation",
        summary = "Nǐ hǎo vs. Nín hǎo, morning greetings, and polite goodbyes.",
        sections = listOf(
            LessonSection(
                number = 1,
                title = "Respect & Tones in Hello",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("Use "),
                            hl("你好"),
                            plain(" (nǐ hǎo) in everyday conversation, and switch to "),
                            hl("您好"),
                            plain(" (nín hǎo) for elders, teachers, and respected guests.")
                        ),
                        audioTerms = listOf(
                            audio("你好", "你好 (Nǐ hǎo)", "Standard Hello"),
                            audio("您好", "您好 (Nín hǎo)", "Respectful Hello to elders"),
                            audio("早上好", "早上好 (Zǎoshang hǎo)", "Good morning"),
                            audio("再见", "再见 (Zàijiàn)", "Goodbye (See you again)")
                        )
                    )
                )
            )
        )
    )

    private fun hindiGuide() = LanguageLessonGuide(
        languageCode = "hi",
        topicTitle = "Greetings & Politeness",
        levelBadge = "A1 Foundation",
        summary = "Namaste etiquette, respectful 'Aap' forms, and warm farewells.",
        sections = listOf(
            LessonSection(
                number = 1,
                title = "Namaste & Respectful Address",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            hl("नमस्ते"),
                            plain(" works at any time of day with folded palms. Always use "),
                            hl("आप"),
                            plain(" (Aap) when asking elders or new acquaintances "),
                            hl("आप कैसे हैं?"),
                            plain(".")
                        ),
                        audioTerms = listOf(
                            audio("नमस्ते", "नमस्ते (Namaste)", "Respectful anytime greeting"),
                            audio("सुप्रभात", "सुप्रभात (Suprabhat)", "Good morning"),
                            audio("आप कैसे हैं?", "आप कैसे हैं? (Aap kaise hain?)", "How are you? (polite)"),
                            audio("फिर मिलेंगे", "फिर मिलेंगे (Phir milenge)", "See you again")
                        )
                    )
                )
            )
        )
    )

    private fun arabicGuide() = LanguageLessonGuide(
        languageCode = "ar",
        topicTitle = "Greetings & Politeness",
        levelBadge = "A1 Foundation",
        summary = "Marhaban, Sabāh al-khayr, and warm polite responses.",
        sections = listOf(
            LessonSection(
                number = 1,
                title = "Welcoming Greetings & Responses",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            hl("مرحبا"),
                            plain(" (Marhaban) is a warm universal hello, while "),
                            hl("صباح الخير"),
                            plain(" is answered with "),
                            hl("صباح النور"),
                            plain(" (Morning of light!).")
                        ),
                        audioTerms = listOf(
                            audio("مرحبا", "مرحبا (Marhaban)", "Warm hello"),
                            audio("صباح الخير", "صباح الخير (Sabāh al-khayr)", "Good morning"),
                            audio("كيف حالك؟", "كيف حالك؟ (Kayfa hāluk?)", "How are you?"),
                            audio("مع السلامة", "مع السلامة (Ma'a as-salāma)", "Goodbye (Go in peace)")
                        )
                    )
                )
            )
        )
    )

    private fun englishGuide() = LanguageLessonGuide(
        languageCode = "en",
        topicTitle = "Greetings & Politeness",
        levelBadge = "A1 Foundation",
        summary = "Hello vs. Hi, time-of-day greetings, polite small talk, and farewells.",
        sections = listOf(
            LessonSection(
                number = 1,
                title = "Formal vs. Casual Greetings",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("Use "),
                            hl("Hello"),
                            plain(" or "),
                            hl("Good morning"),
                            plain(" in professional settings, and "),
                            hl("Hi"),
                            plain(" or "),
                            hl("Hey"),
                            plain(" with friends.")
                        ),
                        audioTerms = listOf(
                            audio("Hello", "Hello", "Universal polite greeting"),
                            audio("Hi!", "Hi!", "Friendly casual greeting"),
                            audio("How are you?", "How are you?", "Polite check-in"),
                            audio("Goodbye", "Goodbye", "Standard farewell")
                        )
                    )
                )
            )
        )
    )
}
