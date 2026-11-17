package com.example

import com.example.data.CurriculumCatalog
import com.example.model.LanguageCatalog
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CurriculumTest {

    @Test
    fun `first unit is Greetings and starts with Hello`() {
        val firstUnit = CurriculumCatalog.units.first()
        assertEquals("Greetings", firstUnit.title)
        val firstPhrase = firstUnit.phrases.first()
        assertEquals("hello", firstPhrase.id)
        assertEquals("Hello", firstPhrase.translationFor("en").text)
        assertEquals("Bonjour", firstPhrase.translationFor("fr").text)
        assertEquals("Hola", firstPhrase.translationFor("es").text)
        assertEquals("Xin chào", firstPhrase.translationFor("vi").text)
        assertEquals("สวัสดี", firstPhrase.translationFor("th").text)
    }

    @Test
    fun `second unit is At the Airport with full 11-turn Agent and Traveler conversation`() {
        val airportUnit = CurriculumCatalog.units[1]
        assertEquals(2, airportUnit.number)
        assertEquals("At the Airport", airportUnit.title)
        assertEquals(11, airportUnit.phrases.size)
        assertEquals("Agent", airportUnit.phrases.first().speakerRole)
        assertEquals("Good morning! Where are you flying today?", airportUnit.phrases.first().translationFor("en").text)
        assertEquals("Traveler", airportUnit.phrases[1].speakerRole)
        assertEquals("Good morning. I am flying to London.", airportUnit.phrases[1].translationFor("en").text)
        assertEquals("Agent", airportUnit.phrases.last().speakerRole)
        assertEquals("You too. Have a good flight!", airportUnit.phrases.last().translationFor("en").text)
    }

    @Test
    fun `thai language is available in catalog`() {
        val thai = LanguageCatalog.allLanguages.find { it.code == "th" }
        assertTrue(thai != null)
        assertEquals("Thai", thai?.englishName)
        assertEquals("th-TH", thai?.localeTag)
    }

    @Test
    fun `every phrase has translations for all supported languages`() {
        val allCodes = LanguageCatalog.allLanguages.map { it.code }
        for (unit in CurriculumCatalog.units) {
            for (phrase in unit.phrases) {
                for (code in allCodes) {
                    val translation = phrase.translations[code]
                    assertTrue(
                        "Missing translation for ${phrase.id} in $code",
                        translation != null && translation.text.isNotBlank()
                    )
                }
            }
        }
    }

    @Test
    fun `my name is phrase substitutes username cleanly`() {
        val greetings = CurriculumCatalog.units.first()
        val myNamePhrase = greetings.phrases.first { it.id == "my_name_is" }
        assertEquals("Je m'appelle Maya", myNamePhrase.translationFor("fr").formattedText("Maya"))
        assertEquals("Me llamo Maya", myNamePhrase.translationFor("es").formattedText("Maya"))
        assertEquals("Tôi tên là Maya", myNamePhrase.translationFor("vi").formattedText("Maya"))
        assertEquals("ฉันชื่อ Maya", myNamePhrase.translationFor("th").formattedText("Maya"))
    }

    @Test
    fun `greetings lesson guide exists for all supported languages with playable highlighted terms`() {
        for (lang in LanguageCatalog.allLanguages) {
            val guide = com.example.data.GreetingsLessonCatalog.guideFor(lang.code)
            assertEquals(lang.code, guide.languageCode)
            assertTrue("Expected sections for ${lang.code}", guide.sections.isNotEmpty())
            val hasAudioTerms = guide.sections.any { sec ->
                sec.paragraphs.any { it.audioTerms.isNotEmpty() } || sec.tableRows.isNotEmpty()
            }
            assertTrue("Expected playable audio terms for ${lang.code}", hasAudioTerms)
        }

        val viGuide = com.example.data.GreetingsLessonCatalog.guideFor("vi")
        assertEquals(5, viGuide.sections.size)
        assertEquals(4, viGuide.sections[1].tableRows.size)

        val thGuide = com.example.data.GreetingsLessonCatalog.guideFor("th")
        assertEquals(6, thGuide.sections.size)
        assertEquals(3, thGuide.sections[3].tableRows.size)

        val frGuide = com.example.data.GreetingsLessonCatalog.guideFor("fr")
        assertEquals(5, frGuide.sections.size)
    }
}
