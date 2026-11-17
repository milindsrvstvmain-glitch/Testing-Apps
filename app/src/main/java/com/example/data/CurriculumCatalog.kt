package com.example.data

import com.example.model.LearningPhrase
import com.example.model.LessonUnit
import com.example.model.PhraseTranslation

private fun t(text: String, phonetic: String, tip: String): PhraseTranslation =
    PhraseTranslation(text = text, phonetic = phonetic, usageNote = tip)

object CurriculumCatalog {

    val units: List<LessonUnit> = listOf(
        LessonUnit(
            id = "unit_greetings",
            number = 1,
            title = "Greetings",
            subtitle = "Basic greetings, introductions & first impressions",
            emoji = "👋",
            phrases = listOf(
                LearningPhrase(
                    id = "hello",
                    shortTitle = "Hello",
                    translations = mapOf(
                        "en" to t("Hello", "heh-LOH", "Universal polite greeting"),
                        "fr" to t("Bonjour", "bohn-ZHOOR", "Polite daytime greeting"),
                        "es" to t("Hola", "OH-lah", "Used any time of day"),
                        "vi" to t("Xin chào", "sin CHOW", "Respectful universal hello"),
                        "th" to t("สวัสดี", "sa-wat-DEE", "Respectful universal hello"),
                        "ja" to t("こんにちは", "kon-nee-chee-wah", "Daytime polite greeting"),
                        "de" to t("Hallo", "HAH-loh", "Friendly standard greeting"),
                        "it" to t("Ciao", "CHOW", "Warm greeting with friends"),
                        "ko" to t("안녕하세요", "ahn-nyung-ha-se-yo", "Polite everyday hello"),
                        "pt" to t("Olá", "oh-LAH", "Friendly anytime greeting"),
                        "zh" to t("你好", "nee HOW", "Standard polite hello"),
                        "hi" to t("नमस्ते", "nuh-mus-TAY", "Respectful greeting"),
                        "ar" to t("مرحبا", "mar-HA-ban", "Warm welcoming hello")
                    )
                ),
                LearningPhrase(
                    id = "hi",
                    shortTitle = "Hi!",
                    translations = mapOf(
                        "en" to t("Hi!", "HY", "Casual friendly greeting"),
                        "fr" to t("Salut !", "sah-LOO", "Casual with friends & peers"),
                        "es" to t("¡Hola! ¿Qué tal?", "OH-lah keh tahl", "Casual friendly hi"),
                        "vi" to t("Chào bạn!", "chow BAHN", "Friendly hello to a peer"),
                        "th" to t("หวัดดี!", "wat-DEE", "Casual friendly hi"),
                        "ja" to t("やあ！", "yah", "Casual greeting among friends"),
                        "de" to t("Hi!", "hy", "Casual everyday hi"),
                        "it" to t("Salve!", "SAHL-veh", "Polite yet relaxed hello"),
                        "ko" to t("안녕!", "ahn-nyung", "Casual hi with close friends"),
                        "pt" to t("Oi!", "OY", "Very common casual hi"),
                        "zh" to t("嗨！", "hy", "Modern casual hi"),
                        "hi" to t("हाय!", "hy", "Casual urban greeting"),
                        "ar" to t("أهلاً!", "AH-lan", "Friendly warm hi")
                    )
                ),
                LearningPhrase(
                    id = "good_morning",
                    shortTitle = "Good morning",
                    translations = mapOf(
                        "en" to t("Good morning", "good MOR-ning", "Morning greeting until noon"),
                        "fr" to t("Bonjour", "bohn-ZHOOR", "Used all morning & afternoon"),
                        "es" to t("Buenos días", "BWEH-nohs DEE-ahs", "Used until midday"),
                        "vi" to t("Chào buổi sáng", "chow BWOY sahng", "Polite morning greeting"),
                        "th" to t("สวัสดีตอนเช้า", "sa-wat-dee tawn CHOW", "Polite morning greeting"),
                        "ja" to t("おはようございます", "oh-ha-yoh go-zai-mas", "Polite morning greeting"),
                        "de" to t("Guten Morgen", "GOO-ten MOR-gen", "Morning until about 11 AM"),
                        "it" to t("Buongiorno", "bwon-JOR-noh", "Morning & early afternoon"),
                        "ko" to t("좋은 아침이에요", "jo-eun ah-chim-i-e-yo", "Warm morning greeting"),
                        "pt" to t("Bom dia", "bohm JEE-ah", "Morning until noon"),
                        "zh" to t("早上好", "dzow-shahng HOW", "Polite morning greeting"),
                        "hi" to t("सुप्रभात", "su-pruh-BHAAT", "Formal morning greeting"),
                        "ar" to t("صباح الخير", "sa-BAH al-khayr", "Classic morning greeting")
                    )
                ),
                LearningPhrase(
                    id = "how_are_you",
                    shortTitle = "How are you?",
                    translations = mapOf(
                        "en" to t("How are you?", "how ahr yoo", "Friendly check-in"),
                        "fr" to t("Comment allez-vous ?", "koh-mohn tah-lay-VOO", "Polite way to ask"),
                        "es" to t("¿Cómo estás?", "KOH-moh ehs-TAHS", "Warm everyday check-in"),
                        "vi" to t("Bạn khỏe không?", "bahn KWEH khohm", "Asking a friend how they are"),
                        "th" to t("สบายดีไหม", "sa-bai DEE mai", "Friendly check-in"),
                        "ja" to t("お元気ですか？", "oh-gen-kee des-kah", "Polite check-in"),
                        "de" to t("Wie geht es Ihnen?", "vee gayt es EE-nen", "Polite formal check-in"),
                        "it" to t("Come stai?", "KOH-meh STY", "Friendly everyday check-in"),
                        "ko" to t("잘 지내세요?", "jal ji-nae-se-yo", "Polite check-in"),
                        "pt" to t("Tudo bem?", "TOO-doo beng", "Literally 'Everything good?'"),
                        "zh" to t("你好吗？", "nee HOW mah", "Simple friendly check-in"),
                        "hi" to t("आप कैसे हैं?", "aap KAI-seh hain", "Polite way to ask"),
                        "ar" to t("كيف حالك؟", "KAY-fa haa-luk", "Classic check-in")
                    )
                ),
                LearningPhrase(
                    id = "im_fine",
                    shortTitle = "I'm fine, thank you",
                    translations = mapOf(
                        "en" to t("I'm fine, thank you", "ym fyn thank yoo", "Polite reply"),
                        "fr" to t("Ça va bien, merci", "sah vah BYAN mehr-SEE", "Natural everyday reply"),
                        "es" to t("Estoy bien, gracias", "ehs-TOY byehn GRAH-syahs", "Warm polite reply"),
                        "vi" to t("Tôi khỏe, cảm ơn bạn", "toy KWEH gahm uhn bahn", "Polite grateful reply"),
                        "th" to t("สบายดี ขอบคุณ", "sa-bai DEE khop-khun", "Warm polite reply"),
                        "ja" to t("元気です、ありがとう", "gen-kee des ah-ree-gah-toh", "Natural polite reply"),
                        "de" to t("Mir geht es gut, danke", "meer gayt es goot DAHN-keh", "Standard polite reply"),
                        "it" to t("Sto bene, grazie", "stoh BEH-neh GRAHT-syeh", "Friendly polite reply"),
                        "ko" to t("잘 지내요, 감사합니다", "jal ji-nae-yo gam-sa-ham-ni-da", "Polite cheerful reply"),
                        "pt" to t("Tudo ótimo, obrigado", "TOO-doo OH-chee-moo", "Warm cheerful reply"),
                        "zh" to t("我很好，谢谢", "woh hun HOW shyeh-shyeh", "Polite standard reply"),
                        "hi" to t("मैं ठीक हूँ, धन्यवाद", "main theek hoon dhun-yuh-vaad", "Polite standard reply"),
                        "ar" to t("أنا بخير، شكراً", "A-na bi-KHAYR shuk-ran", "Warm polite reply")
                    )
                ),
                LearningPhrase(
                    id = "my_name_is",
                    shortTitle = "My name is...",
                    translations = mapOf(
                        "en" to t("My name is {name}", "my naym iz {name}", "Introducing yourself"),
                        "fr" to t("Je m'appelle {name}", "zhuh mah-PEL {name}", "Standard introduction"),
                        "es" to t("Me llamo {name}", "meh YAH-moh {name}", "Literally 'I call myself'"),
                        "vi" to t("Tôi tên là {name}", "toy ten lah {name}", "Clear polite introduction"),
                        "th" to t("ฉันชื่อ {name}", "chan CHUE {name}", "Standard self-introduction"),
                        "ja" to t("私の名前は{name}です", "wah-tah-shee no nah-mah-eh wah {name} des", "Polite self-intro"),
                        "de" to t("Ich heiße {name}", "ikh HY-suh {name}", "Standard introduction"),
                        "it" to t("Mi chiamo {name}", "mee KYAH-moh {name}", "Standard introduction"),
                        "ko" to t("제 이름은 {name}입니다", "je i-reum-eun {name} im-ni-da", "Polite self-intro"),
                        "pt" to t("Meu nome é {name}", "mew NOH-mee eh {name}", "Standard introduction"),
                        "zh" to t("我叫{name}", "woh jyow {name}", "Natural self-introduction"),
                        "hi" to t("मेरा नाम {name} है", "MAY-raa naam {name} hai", "Polite self-intro"),
                        "ar" to t("اسمي {name}", "IS-mee {name}", "Direct polite introduction")
                    )
                ),
                LearningPhrase(
                    id = "nice_to_meet_you",
                    shortTitle = "Nice to meet you",
                    translations = mapOf(
                        "en" to t("Nice to meet you", "nys too meet yoo", "After exchanging names"),
                        "fr" to t("Enchanté", "ohn-shahn-TAY", "Warm & polite upon meeting"),
                        "es" to t("Mucho gusto", "MOO-choh GOOS-toh", "Used across the Spanish world"),
                        "vi" to t("Rất vui được gặp bạn", "zut vwee duhk gahp bahn", "Warm polite greeting"),
                        "th" to t("ยินดีที่ได้รู้จัก", "yin-dee tee dai ROO-jak", "Warm polite greeting"),
                        "ja" to t("はじめまして", "ha-jee-meh-mash-teh", "Said at a first meeting"),
                        "de" to t("Freut mich", "froyt mikh", "Friendly & natural"),
                        "it" to t("Piacere", "pyah-CHEH-reh", "Short & elegant"),
                        "ko" to t("만나서 반갑습니다", "man-na-seo ban-gap-seum-ni-da", "Warm polite greeting"),
                        "pt" to t("Prazer em conhecer", "prah-ZEHR eng kon-yeh-SEHR", "Polite first meeting"),
                        "zh" to t("很高兴认识你", "hun gow-shing run-shih nee", "Warm first-meeting phrase"),
                        "hi" to t("आपसे मिलकर खुशी हुई", "aap-seh mil-kar khu-shee hoo-ee", "Polite warm phrase"),
                        "ar" to t("تشرفت بمعرفتك", "ta-shar-RAFT bi-ma-ri-fa-tik", "Respectful first meeting")
                    )
                ),
                LearningPhrase(
                    id = "goodbye",
                    shortTitle = "Goodbye",
                    translations = mapOf(
                        "en" to t("Goodbye", "good-BY", "Parting phrase"),
                        "fr" to t("Au revoir", "oh ruh-VWAHR", "Literally 'Until we meet again'"),
                        "es" to t("Adiós", "ah-DYOHS", "Standard parting word"),
                        "vi" to t("Tạm biệt", "tahm BYET", "Polite goodbye"),
                        "th" to t("ลาก่อน", "laa GAWN", "Standard goodbye"),
                        "ja" to t("さようなら", "sah-yoh-nah-rah", "Formal farewell"),
                        "de" to t("Auf Wiedersehen", "owf VEE-der-zay-en", "Polite farewell"),
                        "it" to t("Arrivederci", "ah-ree-veh-DER-chee", "Polite 'until we see again'"),
                        "ko" to t("안녕히 가세요", "ahn-nyung-hi ga-se-yo", "Polite goodbye to someone leaving"),
                        "pt" to t("Tchau", "CHOW", "Warm everyday goodbye"),
                        "zh" to t("再见", "dzai-JYEN", "Literally 'See you again'"),
                        "hi" to t("अलविदा", "al-vi-DAA", "Warm farewell"),
                        "ar" to t("مع السلامة", "ma-as-sa-LAA-ma", "Literally 'Go in peace'")
                    )
                )
            )
        ),
        AirportUnitCatalog.unit,
        LessonUnit(
            id = "unit_polite",
            number = 3,
            title = "Polite Essentials",
            subtitle = "Manners, gratitude & everyday courtesy",
            emoji = "✨",
            phrases = listOf(
                LearningPhrase(
                    id = "please",
                    shortTitle = "Please",
                    translations = mapOf(
                        "en" to t("Please", "pleez", "Polite request"),
                        "fr" to t("S'il vous plaît", "seel voo PLEH", "Polite please"),
                        "es" to t("Por favor", "por fah-VOR", "Universal polite please"),
                        "vi" to t("Làm ơn", "lahm UHN", "Polite request starter"),
                        "th" to t("กรุณา", "ga-ru-NAA", "Polite please"),
                        "ja" to t("お願いします", "oh-neh-gai shee-mas", "Polite request"),
                        "de" to t("Bitte", "BIT-tuh", "Please (and you're welcome!)"),
                        "it" to t("Per favore", "per fah-VOH-reh", "Standard polite please"),
                        "ko" to t("부탁합니다", "boo-tak-ham-ni-da", "Polite request"),
                        "pt" to t("Por favor", "por fah-VOR", "Universal polite please"),
                        "zh" to t("请", "ching", "Polite starter before a verb"),
                        "hi" to t("कृपया", "kri-puh-YAA", "Polite please"),
                        "ar" to t("من فضلك", "min FAD-lik", "Polite please")
                    )
                ),
                LearningPhrase(
                    id = "thank_you",
                    shortTitle = "Thank you",
                    translations = mapOf(
                        "en" to t("Thank you", "thank yoo", "Expressing gratitude"),
                        "fr" to t("Merci", "mehr-SEE", "Everyday thank you"),
                        "es" to t("Gracias", "GRAH-syahs", "Everyday thank you"),
                        "vi" to t("Cảm ơn", "gahm UHN", "Sincere thank you"),
                        "th" to t("ขอบคุณ", "khop-KHUN", "Sincere thank you"),
                        "ja" to t("ありがとうございます", "ah-ree-gah-toh go-zai-mas", "Polite thank you"),
                        "de" to t("Danke schön", "DAHN-keh shurn", "Warm thank you"),
                        "it" to t("Grazie", "GRAHT-syeh", "Standard thank you"),
                        "ko" to t("감사합니다", "gam-sa-ham-ni-da", "Polite thank you"),
                        "pt" to t("Obrigado", "oh-bree-GAH-doo", "Standard thank you"),
                        "zh" to t("谢谢", "shyeh-SHYEH", "Everyday thank you"),
                        "hi" to t("धन्यवाद", "dhun-yuh-VAAD", "Polite thank you"),
                        "ar" to t("شكراً", "SHUK-ran", "Universal thank you")
                    )
                ),
                LearningPhrase(
                    id = "youre_welcome",
                    shortTitle = "You're welcome",
                    translations = mapOf(
                        "en" to t("You're welcome", "yor WEL-kum", "Warm reply to thanks"),
                        "fr" to t("De rien", "duh RYAN", "Literally 'It's nothing'"),
                        "es" to t("De nada", "deh NAH-dah", "Literally 'Of nothing'"),
                        "vi" to t("Không có gì", "khohm kaw ZEE", "Literally 'Nothing at all'"),
                        "th" to t("ไม่เป็นไร", "mai pen RAI", "Warm 'You're welcome / No problem'"),
                        "ja" to t("どういたしまして", "doh ee-tah-shee-mash-teh", "Polite you're welcome"),
                        "de" to t("Gern geschehen", "gern guh-SHAY-en", "Happily done"),
                        "it" to t("Prego", "PREH-goh", "Polite & versatile"),
                        "ko" to t("천만에요", "cheon-man-e-yo", "Polite you're welcome"),
                        "pt" to t("De nada", "jee NAH-dah", "Standard reply"),
                        "zh" to t("不客气", "boo KUH-chee", "Don't stand on ceremony"),
                        "hi" to t("कोई बात नहीं", "ko-ee baat nuh-heen", "No problem at all"),
                        "ar" to t("عفواً", "AF-wan", "Polite you're welcome")
                    )
                ),
                LearningPhrase(
                    id = "excuse_me",
                    shortTitle = "Excuse me",
                    translations = mapOf(
                        "en" to t("Excuse me", "eks-KYOOZ mee", "Getting attention or passing by"),
                        "fr" to t("Excusez-moi", "eks-koo-zay MWAH", "Polite pardon/attention"),
                        "es" to t("Disculpe", "dees-KOOL-peh", "Polite excuse me"),
                        "vi" to t("Xin lỗi", "sin LOY", "Used for both excuse me & sorry"),
                        "th" to t("ขอโทษนะ", "khaw-THOT na", "Polite excuse me"),
                        "ja" to t("すみません", "soo-mee-mah-sen", "Polite attention-getter"),
                        "de" to t("Entschuldigung", "ent-SHOOL-dee-gung", "Standard excuse me"),
                        "it" to t("Mi scusi", "mee SKOO-zee", "Polite excuse me"),
                        "ko" to t("실례합니다", "shil-lye-ham-ni-da", "Polite excuse me"),
                        "pt" to t("Com licença", "kohm lee-SEN-sah", "When passing or interrupting"),
                        "zh" to t("不好意思", "boo how EE-suh", "Polite excuse me"),
                        "hi" to t("माफ़ कीजिए", "maaf KEE-jee-yeh", "Polite pardon"),
                        "ar" to t("لو سمحت", "law sa-MAHT", "Polite attention-getter")
                    )
                ),
                LearningPhrase(
                    id = "sorry",
                    shortTitle = "I'm sorry",
                    translations = mapOf(
                        "en" to t("I'm sorry", "ym SOR-ee", "Sincere apology"),
                        "fr" to t("Je suis désolé", "zhuh swee day-zoh-LAY", "Sincere apology"),
                        "es" to t("Lo siento", "loh SYEHN-toh", "I feel it / I'm sorry"),
                        "vi" to t("Tôi xin lỗi", "toy sin LOY", "Polite apology"),
                        "th" to t("ขอโทษครับ", "khaw-THOT khrap", "Sincere apology"),
                        "ja" to t("ごめんなさい", "go-men-nah-sai", "Sincere apology"),
                        "de" to t("Es tut mir leid", "es toot meer lyd", "Sincere apology"),
                        "it" to t("Mi dispiace", "mee dees-PYAH-cheh", "I'm sorry"),
                        "ko" to t("죄송합니다", "jwe-song-ham-ni-da", "Polite apology"),
                        "pt" to t("Desculpe", "des-KOOL-pee", "Standard apology"),
                        "zh" to t("对不起", "dway-boo-CHEE", "Sincere apology"),
                        "hi" to t("मुझे माफ़ करें", "mu-jheh maaf kuh-ren", "Polite apology"),
                        "ar" to t("آسف", "AA-sif", "Standard apology")
                    )
                )
            )
        ),
        LessonUnit(
            id = "unit_conversation",
            number = 4,
            title = "Everyday Chat",
            subtitle = "Questions & keeping conversations flowing",
            emoji = "💬",
            phrases = listOf(
                LearningPhrase(
                    id = "where_from",
                    shortTitle = "Where are you from?",
                    translations = mapOf(
                        "en" to t("Where are you from?", "wair ahr yoo frum", "Getting to know someone"),
                        "fr" to t("D'où venez-vous ?", "doo vuh-nay VOO", "Polite origin question"),
                        "es" to t("¿De dónde eres?", "deh DOHN-deh EH-rehs", "Friendly origin question"),
                        "vi" to t("Bạn đến từ đâu?", "bahn den tuh DOW", "Friendly origin question"),
                        "th" to t("คุณมาจากไหน", "khun maa jaak NAI", "Friendly origin question"),
                        "ja" to t("どちらから来ましたか？", "do-chee-rah kah-rah kee-mash-tah kah", "Polite origin question"),
                        "de" to t("Woher kommen Sie?", "voh-HAIR koh-men zee", "Polite origin question"),
                        "it" to t("Di dove sei?", "dee DOH-veh SAY", "Friendly origin question"),
                        "ko" to t("어디에서 오셨어요?", "eo-di-e-seo o-syeo-sseo-yo", "Polite origin question"),
                        "pt" to t("De onde você é?", "jee OHN-jee voh-SEH eh", "Friendly origin question"),
                        "zh" to t("你从哪里来？", "nee tsohng NAH-lee ly", "Friendly origin question"),
                        "hi" to t("आप कहाँ से हैं?", "aap kuh-HAAN seh hain", "Polite origin question"),
                        "ar" to t("من أين أنت؟", "min AY-na an-ta", "Classic origin question")
                    )
                ),
                LearningPhrase(
                    id = "dont_understand",
                    shortTitle = "I don't understand",
                    translations = mapOf(
                        "en" to t("I don't understand", "y dohnt un-der-STAND", "Helpful learner phrase"),
                        "fr" to t("Je ne comprends pas", "zhuh nuh kohm-prahn PAH", "Essential phrase"),
                        "es" to t("No entiendo", "noh ehn-TYEHN-doh", "Clear & polite"),
                        "vi" to t("Tôi không hiểu", "toy khohm HYEW", "Clear & polite"),
                        "th" to t("ฉันไม่เข้าใจ", "chan mai KHAO-jai", "Clear & polite"),
                        "ja" to t("わかりません", "wah-kah-ree-mah-sen", "Polite I don't understand"),
                        "de" to t("Ich verstehe nicht", "ikh fer-SHTAY-uh nikht", "Standard phrase"),
                        "it" to t("Non capisco", "nohn kah-PEES-koh", "Everyday phrase"),
                        "ko" to t("이해하지 못했어요", "i-hae-ha-ji mo-tae-sseo-yo", "Polite phrase"),
                        "pt" to t("Não entendi", "now en-ten-JEE", "I didn't understand"),
                        "zh" to t("我不明白", "woh boo MING-by", "Clear learner phrase"),
                        "hi" to t("मुझे समझ नहीं आया", "mu-jheh su-majh nuh-heen aa-yaa", "Polite phrase"),
                        "ar" to t("لا أفهم", "laa AF-ham", "Clear essential phrase")
                    )
                ),
                LearningPhrase(
                    id = "speak_slowly",
                    shortTitle = "Please speak slowly",
                    translations = mapOf(
                        "en" to t("Please speak slowly", "pleez speek SLOH-lee", "Great for practice"),
                        "fr" to t("Parlez plus lentement, s'il vous plaît", "par-lay ploo lahnt-mahn", "Polite learner request"),
                        "es" to t("Hable más despacio, por favor", "AH-bleh mahs dehs-PAH-syoh", "Polite learner request"),
                        "vi" to t("Xin hãy nói chậm hơn", "sin hy noy chuhm huhn", "Polite learner request"),
                        "th" to t("กรุณาพูดช้าๆ", "ga-ru-naa phoot CHAA-chaa", "Polite learner request"),
                        "ja" to t("ゆっくり話してください", "yook-koo-ree ha-nah-sh-teh koo-dah-sai", "Polite request"),
                        "de" to t("Sprechen Sie bitte langsamer", "SHPREKH-en zee bit-tuh LAHNG-zah-mer", "Polite request"),
                        "it" to t("Parli più lentamente, per favore", "PAR-lee pyoo len-tah-MEN-teh", "Polite request"),
                        "ko" to t("천천히 말해 주세요", "cheon-cheon-hi mal-hae ju-se-yo", "Polite request"),
                        "pt" to t("Fale mais devagar, por favor", "FAH-lee mys jee-vah-GAR", "Polite request"),
                        "zh" to t("请说慢一点", "ching shwoh MAHN ee-dyen", "Polite request"),
                        "hi" to t("कृपया धीरे बोलिए", "kri-puh-yaa DHEE-reh bo-li-yeh", "Polite request"),
                        "ar" to t("تحدث ببطء من فضلك", "ta-had-dath bi-BUT-in min fad-lik", "Polite request")
                    )
                ),
                LearningPhrase(
                    id = "have_nice_day",
                    shortTitle = "Have a nice day!",
                    translations = mapOf(
                        "en" to t("Have a nice day!", "hav uh nys day", "Warm parting wish"),
                        "fr" to t("Bonne journée !", "bun zhoor-NAY", "Warm daytime farewell"),
                        "es" to t("¡Que tengas un buen día!", "keh TEN-gahs oon bwehn DEE-ah", "Warm parting wish"),
                        "vi" to t("Chúc một ngày tốt lành!", "chook moht ngai toht lahnh", "Warm wish"),
                        "th" to t("ขอให้เป็นวันที่ดี", "khaw hai pen wan tee DEE", "Warm parting wish"),
                        "ja" to t("良い一日を！", "yoi ee-chee-nee-chee oh", "Friendly warm wish"),
                        "de" to t("Schönen Tag noch!", "SHUR-nen tahk nokh", "Friendly parting wish"),
                        "it" to t("Buona giornata!", "BWOH-nah jor-NAH-tah", "Warm daytime wish"),
                        "ko" to t("좋은 하루 보내세요!", "jo-eun ha-ru bo-nae-se-yo", "Cheerful wish"),
                        "pt" to t("Tenha um bom dia!", "TEN-yah oom bohm JEE-ah", "Warm daytime wish"),
                        "zh" to t("祝你今天愉快！", "joo nee jin-tyen yoo-KWY", "Warm daily wish"),
                        "hi" to t("आपका दिन शुभ हो!", "aap-kaa din shubh ho", "Polite warm wish"),
                        "ar" to t("أتمنى لك يوماً سعيداً!", "a-ta-man-na la-ka yaw-man sa-ee-dan", "Warm daily wish")
                    )
                )
            )
        ),
        LessonUnit(
            id = "unit_travel",
            number = 5,
            title = "Cafe & Travel",
            subtitle = "Ordering drinks, getting around & essentials",
            emoji = "☕",
            phrases = listOf(
                LearningPhrase(
                    id = "water_please",
                    shortTitle = "Water, please",
                    translations = mapOf(
                        "en" to t("Water, please", "WAH-ter pleez", "Ordering at a cafe"),
                        "fr" to t("De l'eau, s'il vous plaît", "duh LOH seel voo pleh", "Ordering at a cafe"),
                        "es" to t("Agua, por favor", "AH-gwah por fah-VOR", "Ordering at a cafe"),
                        "vi" to t("Cho tôi nước, làm ơn", "chaw toy nu-uhk lahm uhn", "Polite cafe order"),
                        "th" to t("ขอน้ำเปล่าหน่อย", "khaw nam PLAO noy", "Polite water request"),
                        "ja" to t("お水をください", "oh-mee-zoo oh koo-dah-sai", "Polite restaurant order"),
                        "de" to t("Ein Wasser, bitte", "yn VAH-ser bit-tuh", "Ordering a water"),
                        "it" to t("Dell'acqua, per favore", "del-LAHK-wah per fah-VOH-reh", "Ordering water"),
                        "ko" to t("물 좀 주세요", "mul jom ju-se-yo", "Ordering water"),
                        "pt" to t("Água, por favor", "AH-gwah por fah-VOR", "Ordering water"),
                        "zh" to t("请给我水", "ching gay woh SHWAY", "Polite cafe order"),
                        "hi" to t("कृपया पानी दीजिए", "kri-puh-yaa PAA-nee dee-ji-yeh", "Polite request"),
                        "ar" to t("ماء من فضلك", "maa min FAD-lik", "Polite cafe order")
                    )
                ),
                LearningPhrase(
                    id = "coffee_please",
                    shortTitle = "Coffee, please",
                    translations = mapOf(
                        "en" to t("Coffee, please", "KAW-fee pleez", "Morning cafe order"),
                        "fr" to t("Un café, s'il vous plaît", "uhn kah-FAY seel voo pleh", "Classic French cafe order"),
                        "es" to t("Un café, por favor", "oon kah-FEH por fah-VOR", "Standard coffee order"),
                        "vi" to t("Cho tôi một cà phê", "chaw toy moht kah FEH", "Ordering Vietnamese coffee"),
                        "th" to t("ขอกาแฟหนึ่งแก้ว", "khaw gaa-FAE neung gaeo", "One cup of coffee please"),
                        "ja" to t("コーヒーをください", "koh-hee oh koo-dah-sai", "Polite cafe order"),
                        "de" to t("Einen Kaffee, bitte", "Y-nen KAH-feh bit-tuh", "Standard cafe order"),
                        "it" to t("Un caffè, per favore", "oon kahf-FEH per fah-VOH-reh", "Ordering espresso in Italy"),
                        "ko" to t("커피 한 잔 주세요", "keo-pi han jan ju-se-yo", "One cup of coffee please"),
                        "pt" to t("Um café, por favor", "oom kah-FEH por fah-VOR", "Standard cafe order"),
                        "zh" to t("请给我一杯咖啡", "ching gay woh ee bay kah-fay", "One coffee please"),
                        "hi" to t("एक कॉफ़ी दीजिए", "ek KAW-fee dee-ji-yeh", "Ordering coffee"),
                        "ar" to t("قهوة من فضلك", "QAH-wa min FAD-lik", "Polite coffee order")
                    )
                ),
                LearningPhrase(
                    id = "how_much",
                    shortTitle = "How much is this?",
                    translations = mapOf(
                        "en" to t("How much is this?", "how much iz this", "Shopping & markets"),
                        "fr" to t("Combien ça coûte ?", "kohm-BYAN sah koot", "Asking price"),
                        "es" to t("¿Cuánto cuesta esto?", "KWAHN-toh KWEHS-tah EHS-toh", "Asking price"),
                        "vi" to t("Cái này bao nhiêu tiền?", "ky ny bow nyew TYEN", "Market shopping phrase"),
                        "th" to t("อันนี้ราคาเท่าไหร่", "an-nee raa-khaa THAO-rai", "Market shopping phrase"),
                        "ja" to t("これはいくらですか？", "koh-reh wah ee-koo-rah des-kah", "Asking price"),
                        "de" to t("Wie viel kostet das?", "vee feel KOS-tet dahs", "Asking price"),
                        "it" to t("Quanto costa?", "KWAHN-toh KOHS-tah", "Asking price"),
                        "ko" to t("이거 얼마예요?", "i-geo eol-ma-ye-yo", "Asking price"),
                        "pt" to t("Quanto custa isso?", "KWAHN-too KOOS-tah EE-soo", "Asking price"),
                        "zh" to t("这个多少钱？", "juh-guh DWOH-show chyen", "Asking price"),
                        "hi" to t("यह कितने का है?", "yeh KIT-neh kaa hai", "Asking price"),
                        "ar" to t("بكم هذا؟", "bi-KAM HAA-dha", "Asking price")
                    )
                ),
                LearningPhrase(
                    id = "delicious",
                    shortTitle = "Delicious!",
                    translations = mapOf(
                        "en" to t("Delicious!", "dih-LISH-us", "Complimenting a meal"),
                        "fr" to t("C'est délicieux !", "seh day-lee-SYUH", "Complimenting food"),
                        "es" to t("¡Está delicioso!", "ehs-TAH deh-lee-SYOH-soh", "Complimenting food"),
                        "vi" to t("Ngon quá!", "ngawn KWAH", "So delicious!"),
                        "th" to t("อร่อยมาก!", "a-ROY maak", "Very delicious!"),
                        "ja" to t("おいしいです！", "oh-ee-shee des", "Complimenting food"),
                        "de" to t("Sehr lecker!", "zair LEK-er", "Very tasty!"),
                        "it" to t("Buonissimo!", "bwoh-NEES-see-moh", "Extra delicious!"),
                        "ko" to t("맛있어요!", "ma-shi-sseo-yo", "It's delicious!"),
                        "pt" to t("Está delicioso!", "ehs-TAH deh-lee-SYOH-zoo", "Complimenting food"),
                        "zh" to t("很好吃！", "hun how-CHIH", "Very delicious!"),
                        "hi" to t("बहुत स्वादिष्ट है!", "buh-hut swaa-DISHT hai", "Very delicious!"),
                        "ar" to t("لذيذ جداً!", "la-DHEEDH jid-dan", "Very delicious!")
                    )
                )
            )
        )
    )

    fun findUnit(unitId: String): LessonUnit =
        units.find { it.id == unitId } ?: units.first()
}
