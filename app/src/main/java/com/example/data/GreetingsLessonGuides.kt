package com.example.data

import com.example.model.LanguageLessonGuide
import com.example.model.LessonAudioTerm
import com.example.model.LessonParagraph
import com.example.model.LessonSection
import com.example.model.LessonTableRow
import com.example.model.TextSegment

internal fun plain(text: String): TextSegment = TextSegment(text, isTargetHighlight = false)
internal fun hl(text: String): TextSegment = TextSegment(text, isTargetHighlight = true)
internal fun audio(spoken: String, display: String = spoken, meaning: String = ""): LessonAudioTerm =
    LessonAudioTerm(spokenText = spoken, displayText = display, meaning = meaning)

object GreetingsLessonCatalog {

    private val primaryGuides: Map<String, LanguageLessonGuide> = mapOf(
        "vi" to vietnameseGreetingsGuide(),
        "th" to thaiGreetingsGuide(),
        "fr" to frenchGreetingsGuide()
    )

    fun guideFor(languageCode: String): LanguageLessonGuide =
        primaryGuides[languageCode] ?: MoreGreetingsGuides.guideFor(languageCode)

    private fun vietnameseGreetingsGuide(): LanguageLessonGuide = LanguageLessonGuide(
        languageCode = "vi",
        topicTitle = "Greetings & Politeness",
        levelBadge = "A1 Foundation",
        summary = "Address terms, age-based pronouns, and the magic politeness particle.",
        sections = listOf(
            LessonSection(
                number = 1,
                title = "The Golden Rule: Address Terms (Pronouns)",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("In Vietnamese, you rarely just say \"hello.\" Instead, you address the person by a specific pronoun that reflects your relative age and social standing.")
                        )
                    ),
                    LessonParagraph(
                        segments = listOf(
                            hl("Xin chào"),
                            plain(": This translates literally to \"Please greet,\" and is the standard, polite way to say \"hello\" in textbooks, with foreigners, or in formal announcements. However, native speakers rarely use it with friends or acquaintances in everyday conversation; they prefer using specific pronouns.")
                        ),
                        audioTerms = listOf(
                            audio("Xin chào", "Xin chào", "Standard polite hello")
                        )
                    )
                )
            ),
            LessonSection(
                number = 2,
                title = "Common Greeting Formulas",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("When greeting someone one-on-one, the formula is almost always: "),
                            hl("[Greeting word] + [Pronoun for the other person]"),
                            plain(" (Optionally add "),
                            hl("ạ"),
                            plain(" at the end for extra politeness).")
                        ),
                        audioTerms = listOf(
                            audio("Chào", "Chào", "Greeting word (Hello / Hi)")
                        )
                    ),
                    LessonParagraph(
                        segments = listOf(
                            plain("Here are the most common pronouns you need at the A1 level:")
                        )
                    )
                ),
                tableRows = listOf(
                    LessonTableRow(
                        term = "Em",
                        termSpoken = "Em",
                        literalMeaning = "Younger sibling / child",
                        usageContext = "Someone noticeably younger than you (roughly under 25–30)",
                        exampleText = "Chào em",
                        exampleSpoken = "Chào em",
                        exampleMeaning = "Hello to a younger person"
                    ),
                    LessonTableRow(
                        term = "Anh",
                        termSpoken = "Anh",
                        literalMeaning = "Older brother",
                        usageContext = "An older male (roughly 5 to 15 years older than you)",
                        exampleText = "Chào anh",
                        exampleSpoken = "Chào anh",
                        exampleMeaning = "Hello to an older brother/man"
                    ),
                    LessonTableRow(
                        term = "Chị",
                        termSpoken = "Chị",
                        literalMeaning = "Older sister",
                        usageContext = "An older female (roughly 5 to 15 years older than you)",
                        exampleText = "Chào chị",
                        exampleSpoken = "Chào chị",
                        exampleMeaning = "Hello to an older woman"
                    ),
                    LessonTableRow(
                        term = "Bác / Chú / Cô",
                        termSpoken = "Chào bác, chào chú, chào cô",
                        literalMeaning = "Uncle / Aunt",
                        usageContext = "Older adults belonging to your parents' generation",
                        exampleText = "Chào cô",
                        exampleSpoken = "Chào cô",
                        exampleMeaning = "Hello to an aunt / older woman"
                    )
                )
            ),
            LessonSection(
                number = 3,
                title = "The Magic Particle: Ạ (Politeness Marker)",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("At the end of a greeting or sentence, adding the particle "),
                            hl("ạ"),
                            plain(" shows respect to someone older or in a position of authority.")
                        )
                    ),
                    LessonParagraph(
                        segments = listOf(
                            plain("Example: "),
                            hl("Em chào anh ạ"),
                            plain(" (Hello to you, older brother – polite). As a beginner, using "),
                            hl("ạ"),
                            plain(" when speaking to elders, teachers, or shopkeepers instantly wins warmth and appreciation from native speakers.")
                        ),
                        audioTerms = listOf(
                            audio("Em chào anh ạ", "Em chào anh ạ", "Polite hello to an older man"),
                            audio("Em chào chị ạ", "Em chào chị ạ", "Polite hello to an older woman")
                        )
                    )
                )
            ),
            LessonSection(
                number = 4,
                title = "Time-Specific Greetings",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("While less common in casual modern speech than pronoun-based greetings, you will hear time-of-day greetings in formal settings, media, or service industries:")
                        )
                    ),
                    LessonParagraph(
                        segments = listOf(
                            hl("Chào buổi sáng"),
                            plain(": Good morning · "),
                            hl("Chào buổi chiều"),
                            plain(": Good afternoon · "),
                            hl("Chào buổi tối"),
                            plain(": Good evening")
                        ),
                        audioTerms = listOf(
                            audio("Chào buổi sáng", "Chào buổi sáng", "Good morning"),
                            audio("Chào buổi chiều", "Chào buổi chiều", "Good afternoon"),
                            audio("Chào buổi tối", "Chào buổi tối", "Good evening")
                        )
                    ),
                    LessonParagraph(
                        segments = listOf(
                            plain("Tip: You can attach pronouns to these too, such as "),
                            hl("Chào buổi sáng, anh!"),
                            plain(" (Good morning, older brother/man).")
                        ),
                        audioTerms = listOf(
                            audio("Chào buổi sáng, anh!", "Chào buổi sáng, anh!", "Good morning, sir / older brother")
                        )
                    )
                )
            ),
            LessonSection(
                number = 5,
                title = "Saying Goodbye (Tạm biệt)",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("Saying goodbye is much simpler than greeting:")
                        )
                    ),
                    LessonParagraph(
                        segments = listOf(
                            hl("Tạm biệt"),
                            plain(": Goodbye (standard and polite for all situations).")
                        ),
                        audioTerms = listOf(
                            audio("Tạm biệt", "Tạm biệt", "Standard polite goodbye")
                        )
                    ),
                    LessonParagraph(
                        segments = listOf(
                            hl("Hẹn gặp lại"),
                            plain(": See you again (friendly and natural).")
                        ),
                        audioTerms = listOf(
                            audio("Hẹn gặp lại", "Hẹn gặp lại", "See you again")
                        )
                    )
                )
            )
        )
    )

    private fun thaiGreetingsGuide(): LanguageLessonGuide = LanguageLessonGuide(
        languageCode = "th",
        topicTitle = "Greetings & Politeness",
        levelBadge = "A1 Foundation",
        summary = "Polite gender particles, Sawatdee, social titles, the Wai gesture & goodbyes.",
        sections = listOf(
            LessonSection(
                number = 1,
                title = "The Polite Particles (The Heart of Thai Politeness)",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("In Thai, your sentence-ending particle changes depending on your speaker gender. This is mandatory for sounding polite, whether you are saying hello, thank you, or yes.")
                        )
                    ),
                    LessonParagraph(
                        segments = listOf(
                            plain("• Male speakers end sentences with "),
                            hl("ครับ (Krap)"),
                            plain(" with a high rising or clipped high tone.")
                        ),
                        audioTerms = listOf(
                            audio("ครับ", "ครับ (Krap)", "Polite ending particle (male speaker)")
                        )
                    ),
                    LessonParagraph(
                        segments = listOf(
                            plain("• Female speakers end sentences with "),
                            hl("ค่ะ (Ka)"),
                            plain(" with a falling tone (or "),
                            hl("คะ (Ka)"),
                            plain(" with a high tone when asking a question).")
                        ),
                        audioTerms = listOf(
                            audio("ค่ะ", "ค่ะ (Ka)", "Polite ending particle (female statement)"),
                            audio("คะ", "คะ (Ka)", "Polite ending particle (female question)")
                        )
                    )
                )
            ),
            LessonSection(
                number = 2,
                title = "The Universal Greeting: Sawatdee",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("Unlike English, where \"hello\" stands alone, Thai greetings combine the greeting root word "),
                            hl("สวัสดี (Sawatdee)"),
                            plain(" with your polite particle:")
                        )
                    ),
                    LessonParagraph(
                        segments = listOf(
                            hl("สวัสดีครับ (Sawatdee krap)"),
                            plain(": Hello (spoken by a male speaker) · "),
                            hl("สวัสดีค่ะ (Sawatdee ka)"),
                            plain(": Hello (spoken by a female speaker)")
                        ),
                        audioTerms = listOf(
                            audio("สวัสดีครับ", "สวัสดีครับ (Sawatdee krap)", "Hello — male speaker"),
                            audio("สวัสดีค่ะ", "สวัสดีค่ะ (Sawatdee ka)", "Hello — female speaker")
                        )
                    ),
                    LessonParagraph(
                        segments = listOf(
                            plain("Note: While "),
                            hl("สวัสดี"),
                            plain(" can technically be used at any time of day, native speakers use it as a general catch-all greeting.")
                        ),
                        audioTerms = listOf(
                            audio("สวัสดี", "สวัสดี (Sawatdee)", "Universal greeting root")
                        )
                    )
                )
            ),
            LessonSection(
                number = 3,
                title = "Time-Specific Greetings",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("Thai has formal time-based greetings, though they are often reserved for speeches, media, or professional environments (in casual chat, people stick to "),
                            hl("สวัสดี"),
                            plain("):")
                        ),
                        audioTerms = listOf(
                            audio("อรุณสวัสดิ์", "อรุณสวัสดิ์ (Arun sawat)", "Good morning (formal)"),
                            audio("วันสวัสดิ์", "วันสวัสดิ์ (Wann-sawat)", "Good day"),
                            audio("เย็นสวัสดิ์", "เย็นสวัสดิ์ (Yen-sawat)", "Good evening")
                        )
                    )
                )
            ),
            LessonSection(
                number = 4,
                title = "Addressing People (Age and Status)",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("Just like Vietnamese, Thai culture places strong emphasis on seniority. Addressing people by their relative social status shows warm respect:")
                        )
                    )
                ),
                tableRows = listOf(
                    LessonTableRow(
                        term = "พี่ (Phi)",
                        termSpoken = "พี่",
                        literalMeaning = "Older sibling / peer",
                        usageContext = "Someone slightly older than you",
                        exampleText = "สวัสดีพี่",
                        exampleSpoken = "สวัสดีพี่",
                        exampleMeaning = "Hello to an older person"
                    ),
                    LessonTableRow(
                        term = "น้อง (Nong)",
                        termSpoken = "น้อง",
                        literalMeaning = "Younger sibling",
                        usageContext = "Someone younger than you",
                        exampleText = "สวัสดีน้อง",
                        exampleSpoken = "สวัสดีน้อง",
                        exampleMeaning = "Hello to a younger person"
                    ),
                    LessonTableRow(
                        term = "คุณ (Khun)",
                        termSpoken = "คุณ",
                        literalMeaning = "Mr. / Ms. / Polite You",
                        usageContext = "Business settings, strangers of similar age, customer service",
                        exampleText = "สวัสดีคุณ",
                        exampleSpoken = "สวัสดีคุณ",
                        exampleMeaning = "Hello, sir / madam"
                    )
                )
            ),
            LessonSection(
                number = 5,
                title = "The Wai (The Non-Verbal Greeting)",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("In Thailand, words are accompanied by a gesture called the "),
                            hl("ไหว้ (Wai)"),
                            plain("—placing your palms together in a prayer-like gesture near your chest or face while bowing slightly.")
                        ),
                        audioTerms = listOf(
                            audio("ไหว้", "ไหว้ (Wai)", "Traditional Thai respectful greeting gesture")
                        )
                    ),
                    LessonParagraph(
                        segments = listOf(
                            plain("The Rule of Respect: The younger or lower-status person initiates the Wai. You generally do not Wai a child or casual service worker unless they Wai you first. For A1 learners, a polite "),
                            hl("สวัสดี"),
                            plain(" with a smile and a light nod works well with peers, while mastering the Wai for elders and teachers is essential.")
                        )
                    )
                )
            ),
            LessonSection(
                number = 6,
                title = "Saying Goodbye",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("Saying goodbye in Thai is straightforward:")
                        ),
                        audioTerms = listOf(
                            audio("ลาก่อนครับ", "ลาก่อนครับ (La korn krap)", "Goodbye — formal, male speaker"),
                            audio("ลาก่อนค่ะ", "ลาก่อนค่ะ (La korn ka)", "Goodbye — formal, female speaker"),
                            audio("พบกันใหม่ครับ", "พบกันใหม่ครับ (Phob kan mai krap)", "See you again — natural, male"),
                            audio("พบกันใหม่ค่ะ", "พบกันใหม่ค่ะ (Phob kan mai ka)", "See you again — natural, female"),
                            audio("เจอกัน", "เจอกัน (Jeo gan)", "See you — casual with close friends")
                        )
                    )
                )
            )
        )
    )

    private fun frenchGreetingsGuide(): LanguageLessonGuide = LanguageLessonGuide(
        languageCode = "fr",
        topicTitle = "Greetings & Politeness",
        levelBadge = "A1 Foundation",
        summary = "Bonjour vs. Salut, evening rules, polite titles, Ça va?, and farewells.",
        sections = listOf(
            LessonSection(
                number = 1,
                title = "The Big Divide: Bonjour vs. Salut",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("Choosing the right greeting in French depends entirely on who you are talking to and the setting:")
                        )
                    ),
                    LessonParagraph(
                        segments = listOf(
                            hl("Bonjour"),
                            plain(" (Good day / Hello): Your universal, go-to greeting. Use it with shopkeepers, teachers, strangers, colleagues, and in any polite setting.")
                        ),
                        audioTerms = listOf(
                            audio("Bonjour", "Bonjour", "Universal polite daytime greeting")
                        )
                    ),
                    LessonParagraph(
                        segments = listOf(
                            hl("Salut"),
                            plain(" (Hi / Bye): Strictly informal. Use it exclusively with friends, family, children, and peers your own age. Note: "),
                            hl("Salut"),
                            plain(" can mean both hello and goodbye!")
                        ),
                        audioTerms = listOf(
                            audio("Salut !", "Salut !", "Casual Hi / Bye with friends")
                        )
                    )
                )
            ),
            LessonSection(
                number = 2,
                title = "Time-Specific Greetings",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("While "),
                            hl("bonjour"),
                            plain(" works all day until evening, French switches for nighttime:")
                        ),
                        audioTerms = listOf(
                            audio("Bonsoir", "Bonsoir", "Good evening (from sunset onward)"),
                            audio("Bonne nuit", "Bonne nuit", "Goodnight — only when going to sleep")
                        )
                    ),
                    LessonParagraph(
                        segments = listOf(
                            hl("Bonsoir"),
                            plain(" (Good evening): Switch to bonsoir around sunset when arriving at a restaurant, shop, or evening gathering. "),
                            hl("Bonne nuit"),
                            plain(" (Good night) means strictly \"Goodnight, sleep well\" right before someone goes to bed.")
                        )
                    )
                )
            ),
            LessonSection(
                number = 3,
                title = "The Polite Title Add-ons",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("When greeting someone formally in France, it is customary to attach a title after "),
                            hl("Bonjour"),
                            plain(" or "),
                            hl("Bonsoir"),
                            plain(". Unlike Vietnamese or Thai, these are standard politeness markers rather than relative age pronouns:")
                        ),
                        audioTerms = listOf(
                            audio("Bonjour, monsieur", "Bonjour, monsieur", "Good morning, sir / Mr."),
                            audio("Bonjour, madame", "Bonjour, madame", "Good morning, ma'am / Mrs."),
                            audio("Bonjour, mademoiselle", "Bonjour, mademoiselle", "Good morning, miss (madame is now standard)")
                        )
                    )
                )
            ),
            LessonSection(
                number = 4,
                title = "The Golden A1 Survival Rule: Ça va?",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("After saying hello, native speakers almost always follow up with a quick well-being check:")
                        )
                    ),
                    LessonParagraph(
                        segments = listOf(
                            plain("Formal/Polite: "),
                            hl("Bonjour monsieur, comment allez-vous ?"),
                            plain(" — Reply: "),
                            hl("Je vais bien, merci.")
                        ),
                        audioTerms = listOf(
                            audio("Bonjour monsieur, comment allez-vous ?", "Bonjour monsieur, comment allez-vous ?", "Hello sir, how are you?"),
                            audio("Je vais bien, merci.", "Je vais bien, merci.", "I am doing well, thank you.")
                        )
                    ),
                    LessonParagraph(
                        segments = listOf(
                            plain("Casual/Informal: "),
                            hl("Salut ! Ça va ?"),
                            plain(" — Reply: "),
                            hl("Ça va, et toi ?")
                        ),
                        audioTerms = listOf(
                            audio("Salut ! Ça va ?", "Salut ! Ça va ?", "Hi! How's it going?"),
                            audio("Ça va, et toi ?", "Ça va, et toi ?", "It's going well, and you?")
                        )
                    )
                )
            ),
            LessonSection(
                number = 5,
                title = "Saying Goodbye",
                paragraphs = listOf(
                    LessonParagraph(
                        segments = listOf(
                            plain("Just like greetings, goodbyes depend on how well you know the person:")
                        ),
                        audioTerms = listOf(
                            audio("Au revoir", "Au revoir", "Goodbye — standard and polite everywhere"),
                            audio("À bientôt", "À bientôt", "See you soon — friendly and common"),
                            audio("À demain", "À demain", "See you tomorrow"),
                            audio("Salut", "Salut", "Bye — informal with friends")
                        )
                    )
                )
            )
        )
    )
}
