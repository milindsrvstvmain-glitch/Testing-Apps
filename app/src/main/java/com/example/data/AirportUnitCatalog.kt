package com.example.data

import com.example.model.LearningPhrase
import com.example.model.LessonUnit
import com.example.model.PhraseTranslation

private fun at(text: String, phonetic: String, tip: String): PhraseTranslation =
    PhraseTranslation(text = text, phonetic = phonetic, usageNote = tip)

object AirportUnitCatalog {

    val unit: LessonUnit = LessonUnit(
        id = "unit_airport",
        number = 2,
        title = "At the Airport",
        subtitle = "Check-in desk conversation: passport, bags & boarding",
        emoji = "✈️",
        phrases = listOf(
            LearningPhrase(
                id = "airport_1_where_flying",
                shortTitle = "Where are you flying?",
                speakerRole = "Agent",
                translations = mapOf(
                    "en" to at("Good morning! Where are you flying today?", "good MOR-ning wair ahr yoo FLY-ing tuh-DAY", "Check-in desk greeting"),
                    "fr" to at("Bonjour ! Où voyagez-vous aujourd'hui ?", "bohn-ZHOOR oo vwah-yah-zhay VOO oh-zhoor-DWEE", "Polite check-in question"),
                    "es" to at("¡Buenos días! ¿A dónde vuela hoy?", "BWEH-nohs DEE-ahs ah DOHN-deh BWEH-lah oy", "Formal airline check-in"),
                    "vi" to at("Chào buổi sáng! Hôm nay bạn bay đi đâu?", "chow BWOY sahng hohm ny bahn BY dee dow", "Friendly airline agent greeting"),
                    "th" to at("สวัสดีตอนเช้าค่ะ วันนี้คุณจะบินไปที่ไหนคะ", "sa-wat-dee tawn CHOW kha wan-nee khun ja bin pai tee NAI kha", "Polite Thai airport check-in"),
                    "ja" to at("おはようございます。本日はどちらへご出発ですか？", "oh-ha-yoh go-zai-mas hon-jee-tsu wa do-chee-rah eh go-shup-patsu des-kah", "Polite check-in greeting"),
                    "de" to at("Guten Morgen! Wohin fliegen Sie heute?", "GOO-ten MOR-gen voh-HIN FLEE-gen zee HOY-tuh", "Standard check-in question"),
                    "it" to at("Buongiorno! Dove vola oggi?", "bwon-JOR-noh DOH-veh VOH-lah OHJ-jee", "Polite check-in question"),
                    "ko" to at("좋은 아침입니다! 오늘 어디로 가시나요?", "jo-eun ah-chim-im-ni-da o-neul eo-di-ro ga-si-na-yo", "Polite airline greeting"),
                    "pt" to at("Bom dia! Para onde você vai voar hoje?", "bohm JEE-ah PAH-rah OHN-jee voh-SEH vy voh-AR OH-zhee", "Check-in desk question"),
                    "zh" to at("早上好！您今天飞往哪里？", "dzow-shahng HOW neen jin-tyen fay-wahng NAH-lee", "Polite check-in question"),
                    "hi" to at("सुप्रभात! आज आप कहाँ की उड़ान भर रहे हैं?", "su-pruh-BHAAT aaj aap kuh-HAAN kee u-daan bhar ruh-heh hain", "Polite check-in greeting"),
                    "ar" to at("صباح الخير! إلى أين تسافر اليوم؟", "sa-BAH al-khayr i-la AY-na tu-SAA-fir al-YAWM", "Polite airport greeting")
                )
            ),
            LearningPhrase(
                id = "airport_2_flying_london",
                shortTitle = "Flying to London",
                speakerRole = "Traveler",
                translations = mapOf(
                    "en" to at("Good morning. I am flying to London.", "good MOR-ning ym FLY-ing too LUN-dun", "Stating your destination"),
                    "fr" to at("Bonjour. Je prends l'avion pour Londres.", "bohn-ZHOOR zhuh prahn lah-VYOHN poor LOHN-druh", "London = Londres in French"),
                    "es" to at("Buenos días. Vuelo a Londres.", "BWEH-nohs DEE-ahs BWEH-loh ah LOHN-drehs", "London = Londres in Spanish"),
                    "vi" to at("Chào buổi sáng. Tôi bay đi Luân Đôn.", "chow BWOY sahng toy BY dee lwan DOHN", "Stating destination clearly"),
                    "th" to at("สวัสดีตอนเช้าครับ ฉันกำลังจะบินไปลอนดอน", "sa-wat-dee tawn CHOW khrap chan gam-lang ja bin pai lon-DON", "Stating destination in Thai"),
                    "ja" to at("おはようございます。ロンドン行きです。", "oh-ha-yoh go-zai-mas ron-don yoo-kee des", "I am bound for London"),
                    "de" to at("Guten Morgen. Ich fliege nach London.", "GOO-ten MOR-gen ikh FLEE-guh nahkh LON-don", "Stating destination"),
                    "it" to at("Buongiorno. Volo a Londra.", "bwon-JOR-noh VOH-loh ah LOHN-drah", "London = Londra in Italian"),
                    "ko" to at("좋은 아침입니다. 런던으로 갑니다.", "jo-eun ah-chim-im-ni-da reon-deo-neu-ro gam-ni-da", "Stating destination"),
                    "pt" to at("Bom dia. Estou voando para Londres.", "bohm JEE-ah ehs-TOH voh-AHN-doo PAH-rah LOHN-drees", "Stating destination"),
                    "zh" to at("早上好。我飞往伦敦。", "dzow-shahng HOW woh fay-wahng lun-DWUN", "Stating destination"),
                    "hi" to at("सुप्रभात। मैं लंदन जा रहा हूँ।", "su-pruh-BHAAT main LAN-dan jaa ruh-haa hoon", "Stating destination"),
                    "ar" to at("صباح الخير. أنا مسافر إلى لندن.", "sa-BAH al-khayr a-na mu-SAA-fir i-la LAN-dan", "Stating destination")
                )
            ),
            LearningPhrase(
                id = "airport_3_passport_ticket",
                shortTitle = "Passport & ticket",
                speakerRole = "Agent",
                translations = mapOf(
                    "en" to at("May I see your passport and ticket, please?", "may y see yor PAS-port and TIK-it pleez", "Document check"),
                    "fr" to at("Puis-je voir votre passeport et votre billet, s'il vous plaît ?", "pwee-zhuh vwar voh-truh pas-POR ay voh-truh bee-YEH seel voo pleh", "Polite document request"),
                    "es" to at("¿Puedo ver su pasaporte y su boleto, por favor?", "PWEH-doh vehr soo pah-sah-POR-teh ee soo boh-LEH-toh por fah-VOR", "Polite document request"),
                    "vi" to at("Cho tôi xem hộ chiếu và vé của bạn được không?", "chaw toy sem hoh CHYEW vah VEH koo-uh bahn duhk khohm", "Polite document check"),
                    "th" to at("ขอดูหนังสือเดินทางและตั๋วเครื่องบินหน่อยได้ไหมคะ", "khaw doo nang-sue dern-taang lae tua khrueang-bin noy dai mai kha", "Requesting passport and ticket"),
                    "ja" to at("パスポートと航空券を拝見できますか？", "pa-su-poh-toh toh koh-koo-ken oh hai-ken deh-kee-mas-kah", "Polite document request"),
                    "de" to at("Darf ich bitte Ihren Reisepass und Ihr Ticket sehen?", "darf ikh bit-tuh EE-ren RY-zuh-pahs oont eer TIK-et ZAY-en", "Polite document request"),
                    "it" to at("Posso vedere il suo passaporto e il biglietto, per favore?", "POHS-soh veh-DEH-reh eel soo-oh pahs-sah-POR-toh ay eel bee-LYET-toh", "Polite document request"),
                    "ko" to at("여권과 항공권을 보여주시겠어요?", "yeo-gwon-gwa hang-gong-gwo-neul bo-yeo-ju-si-ge-sseo-yo", "Polite document request"),
                    "pt" to at("Posso ver seu passaporte e sua passagem, por favor?", "POH-soo vehr sew pah-sah-POR-chee ee soo-ah pah-SAH-zheng", "Polite document request"),
                    "zh" to at("请出示您的护照和机票好吗？", "ching choo-shih neen-duh hoo-jow huh jee-pyow how mah", "Polite document request"),
                    "hi" to at("कृपया अपना पासपोर्ट और टिकट दिखाएँ।", "kri-puh-yaa ap-naa PAAS-port aur TIK-at di-khaa-yen", "Polite document request"),
                    "ar" to at("هل يمكنني رؤية جواز سفرك وتذكرتك من فضلك؟", "hal yum-ki-nu-nee ru-ya-tu ja-waa-zi sa-fa-ri-ka wa tadh-ki-ra-tik", "Polite document request")
                )
            ),
            LearningPhrase(
                id = "airport_4_here_you_are",
                shortTitle = "Yes, here you are",
                speakerRole = "Traveler",
                translations = mapOf(
                    "en" to at("Yes, here you are.", "yes heer yoo ahr", "Handing over documents"),
                    "fr" to at("Oui, tenez.", "wee tuh-NAY", "Polite 'Here you go'"),
                    "es" to at("Sí, aquí tiene.", "see ah-KEE TYEH-neh", "Handing something politely"),
                    "vi" to at("Vâng, của bạn đây.", "vuhng koo-uh bahn DAY", "Polite 'Here you are'"),
                    "th" to at("ครับ นี่ครับ", "khrap NEE khrap", "Polite 'Yes, here you are'"),
                    "ja" to at("はい、どうぞ。", "hai DOH-zoh", "Polite 'Yes, please take it'"),
                    "de" to at("Ja, bitte schön.", "yah BIT-tuh shurn", "Handing something over"),
                    "it" to at("Sì, ecco a lei.", "see EK-koh ah lay", "Formal 'Here you are'"),
                    "ko" to at("네, 여기 있습니다.", "ne yeo-gi it-seum-ni-da", "Polite 'Yes, here it is'"),
                    "pt" to at("Sim, aqui está.", "seng ah-KEE ehs-TAH", "Handing over politely"),
                    "zh" to at("好的，给您。", "how-duh gay NEEN", "Polite 'Here you go'"),
                    "hi" to at("जी हाँ, यह लीजिए।", "jee haan yeh LEE-ji-yeh", "Polite 'Yes, please take this'"),
                    "ar" to at("نعم، تفضل.", "na-AM ta-FAD-dal", "Polite 'Yes, here you are'")
                )
            ),
            LearningPhrase(
                id = "airport_5_bags_to_check",
                shortTitle = "Bags to check?",
                speakerRole = "Agent",
                translations = mapOf(
                    "en" to at("Thank you. Do you have any bags to check?", "thank yoo doo yoo hav EN-ee bagz too chek", "Luggage check question"),
                    "fr" to at("Merci. Avez-vous des bagages à enregistrer ?", "mehr-SEE ah-vay-VOO day bah-GAHZH ah ahn-ruh-zhees-TRAY", "Checking luggage"),
                    "es" to at("Gracias. ¿Tiene maletas para documentar?", "GRAH-syahs TYEH-neh mah-LEH-tahs PAH-rah doh-koo-mehn-TAR", "Checking luggage"),
                    "vi" to at("Cảm ơn bạn. Bạn có hành lý ký gửi không?", "gahm uhn bahn bahn kaw hahnh LEE kee guy khohm", "Asking about checked bags"),
                    "th" to at("ขอบคุณค่ะ มีกระเป๋าที่จะโหลดใต้ท้องเครื่องไหมคะ", "khop-khun kha mee gra-pao tee ja loht tai tawng khrueang mai kha", "Asking about checked luggage"),
                    "ja" to at("ありがとうございます。お預けになる手荷物はございますか？", "ah-ree-gah-toh go-zai-mas oh-ah-zoo-keh nee nah-roo teh-nee-moh-tsu wa go-zai-mas-kah", "Polite baggage inquiry"),
                    "de" to at("Danke. Haben Sie Gepäck zum Aufgeben?", "DAHN-keh HAH-ben zee guh-PEK tsoom OWF-gay-ben", "Asking about checked luggage"),
                    "it" to at("Grazie. Ha bagagli da imbarcare?", "GRAHT-syeh ah bah-GAH-lyee dah eem-bar-KAH-reh", "Asking about checked luggage"),
                    "ko" to at("감사합니다. 부치실 수하물이 있으신가요?", "gam-sa-ham-ni-da bu-chi-sil su-ha-mu-ri it-seu-sin-ga-yo", "Asking about checked luggage"),
                    "pt" to at("Obrigado. Você tem malas para despachar?", "oh-bree-GAH-doo voh-SEH teng MAH-lahs PAH-rah dehs-pah-SHAR", "Checking luggage"),
                    "zh" to at("谢谢。您有需要托运的行李吗？", "shyeh-shyeh neen yo shoo-yow twoh-yoon duh shing-lee mah", "Asking about checked bags"),
                    "hi" to at("धन्यवाद। क्या आपके पास चेक-इन करने के लिए कोई बैग है?", "dhun-yuh-vaad kyaa aap-keh paas check-in ke li-yeh koi bag hai", "Asking about checked luggage"),
                    "ar" to at("شكراً. هل لديك حقائب لتسجيلها؟", "shuk-ran hal la-DAY-ka ha-QAA-ib li-tas-jee-li-haa", "Asking about checked bags")
                )
            ),
            LearningPhrase(
                id = "airport_6_one_suitcase",
                shortTitle = "One suitcase",
                speakerRole = "Traveler",
                translations = mapOf(
                    "en" to at("Yes, I have one suitcase.", "yes y hav wun SOOT-kays", "Confirming one checked bag"),
                    "fr" to at("Oui, j'ai une valise.", "wee zhay oon vah-LEEZ", "Valise = suitcase"),
                    "es" to at("Sí, tengo una maleta.", "see TEN-goh OO-nah mah-LEH-tah", "Maleta = suitcase"),
                    "vi" to at("Vâng, tôi có một chiếc vali.", "vuhng toy kaw moht CHYEK vah-LEE", "Confirming one suitcase"),
                    "th" to at("ครับ มีกระเป๋าเดินทางหนึ่งใบครับ", "khrap mee gra-pao dern-taang neung bai khrap", "One suitcase ('bai' is bag classifier)"),
                    "ja" to at("はい、スーツケースが一つあります。", "hai soo-tsu-keh-su ga hee-toh-tsu ah-ree-mas", "One suitcase"),
                    "de" to at("Ja, ich habe einen Koffer.", "yah ikh HAH-buh Y-nen KOF-fer", "Koffer = suitcase"),
                    "it" to at("Sì, ho una valigia.", "see oh OO-nah vah-LEE-jah", "Valigia = suitcase"),
                    "ko" to at("네, 여행 가방 하나 있습니다.", "ne yeo-haeng ga-bang ha-na it-seum-ni-da", "One suitcase"),
                    "pt" to at("Sim, eu tenho uma mala.", "seng ew TEN-yoo OO-mah MAH-lah", "Mala = suitcase"),
                    "zh" to at("是的，我有一个行李箱。", "shih-duh woh yo EE-guh shing-lee-shyahng", "One suitcase"),
                    "hi" to at("जी हाँ, मेरे पास एक सूटकेस है।", "jee haan meh-reh paas ek SOOT-kes hai", "Confirming one suitcase"),
                    "ar" to at("نعم، لدي حقيبة واحدة.", "na-AM la-DAY-ya ha-QEE-ba WAA-hi-da", "One suitcase")
                )
            ),
            LearningPhrase(
                id = "airport_7_scale_seat",
                shortTitle = "Scale & seat preference",
                speakerRole = "Agent",
                translations = mapOf(
                    "en" to at("Please put it on the scale. Thank you. Do you prefer a window seat or an aisle seat?", "pleez put it on the skayl ... doo yoo prih-FUR uh WIN-doh seet or an YL seet", "Weighing bag & seat choice"),
                    "fr" to at("Posez-la sur la balance, s'il vous plaît. Merci. Préférez-vous un hublot ou un couloir ?", "poh-zay lah soor lah bah-LAHNS ... pray-fay-ray VOO uhn oo-BLOH oo uhn koo-LWAR", "Hublot = window, couloir = aisle"),
                    "es" to at("Póngala en la báscula, por favor. Gracias. ¿Prefiere ventanilla o pasillo?", "POHN-gah-lah en lah BAHS-koo-lah ... preh-FYEH-reh ven-tah-NEE-yah oh pah-SEE-yoh", "Ventanilla = window, pasillo = aisle"),
                    "vi" to at("Xin đặt lên cân. Cảm ơn. Bạn thích ghế cửa sổ hay ghế lối đi?", "sin daht len kuhn ... bahn tik geh koo-uh SOH hy geh loy DEE", "Window vs aisle seat"),
                    "th" to at("กรุณาวางบนตาชั่งค่ะ ขอบคุณค่ะ ชอบที่นั่งริมหน้าต่างหรือริมทางเดินคะ", "ga-ru-naa waang bon taa-CHANG kha ... chawp tee-nang rim naa-TAANG rue rim taang-DERN kha", "Window (naa-taang) or aisle (taang-dern)"),
                    "ja" to at("計量台にお載せください。ありがとうございます。窓側と通路側、どちらがよろしいですか？", "keh-ryoh-dai nee oh-noh-seh koo-dah-sai ... mah-doh-gah-wah toh tsoo-roh-gah-wah do-chee-rah ga", "Window vs aisle seat"),
                    "de" to at("Bitte stellen Sie ihn auf die Waage. Danke. Möchten Sie einen Fenster- oder Gangplatz?", "bit-tuh SHTEL-len zee een owf dee VAH-guh ... FEN-ster oh-der GAHNG-plahts", "Fenster = window, Gang = aisle"),
                    "it" to at("La metta sulla bilancia, per favore. Grazie. Preferisce un posto finestrino o corridoio?", "lah MET-tah sool-lah bee-LAHN-chah ... fee-nes-TREE-noh oh kor-ree-DOY-oh", "Window vs aisle seat"),
                    "ko" to at("저울 위에 올려주세요. 감사합니다. 창가 좌석과 복도 좌석 중 어느 쪽을 원하시나요?", "jeo-ul wi-e ol-lyeo-ju-se-yo ... chang-ga jwa-seok-gwa bok-do jwa-seok", "Window vs aisle seat"),
                    "pt" to at("Coloque na balança, por favor. Obrigado. Prefere janela ou corredor?", "koh-LOH-kee nah bah-LAHN-sah ... jah-NEH-lah oh koh-heh-DOR", "Janela = window, corredor = aisle"),
                    "zh" to at("请把它放在秤上。谢谢。您想要靠窗还是靠走廊的座位？", "ching bah tah fahng dzai CHUNG shahng ... kow CHWAHNG hy-shih kow DZOH-lahng", "Window vs aisle seat"),
                    "hi" to at("कृपया इसे तराजू पर रखें। धन्यवाद। क्या आपको विंडो सीट पसंद है या आइल सीट?", "kri-puh-yaa i-seh tuh-RAA-joo par rakh-en ... WINDOW seat yaa AISLE seat", "Weighing & seat choice"),
                    "ar" to at("ضَعها على الميزان من فضلك. شكراً. هل تفضل مقعداً بجانب النافذة أم الممر؟", "da-haa a-la al-mee-ZAAN ... bi-jaa-ni-bi an-NAA-fi-dha am al-ma-MARR", "Window vs aisle seat")
                )
            ),
            LearningPhrase(
                id = "airport_8_window_seat",
                shortTitle = "A window seat, please",
                speakerRole = "Traveler",
                translations = mapOf(
                    "en" to at("A window seat, please.", "uh WIN-doh seet pleez", "Choosing your seat"),
                    "fr" to at("Un hublot, s'il vous plaît.", "uhn oo-BLOH seel voo pleh", "Choosing window seat"),
                    "es" to at("De ventanilla, por favor.", "deh ven-tah-NEE-yah por fah-VOR", "Choosing window seat"),
                    "vi" to at("Cho tôi ghế cửa sổ, làm ơn.", "chaw toy geh koo-uh SOH lahm uhn", "Choosing window seat"),
                    "th" to at("ขอที่นั่งริมหน้าต่างครับ", "khaw tee-nang rim naa-TAANG khrap", "Requesting window seat"),
                    "ja" to at("窓側をお願いします。", "mah-doh-gah-wah oh oh-neh-gai shee-mas", "Choosing window seat"),
                    "de" to at("Einen Fensterplatz, bitte.", "Y-nen FEN-ster-plahts bit-tuh", "Choosing window seat"),
                    "it" to at("Un posto finestrino, per favore.", "oon POHS-toh fee-nes-TREE-noh per fah-VOH-reh", "Choosing window seat"),
                    "ko" to at("창가 좌석으로 주세요.", "chang-ga jwa-seo-geu-ro ju-se-yo", "Choosing window seat"),
                    "pt" to at("Um assento na janela, por favor.", "oom ah-SEN-too nah jah-NEH-lah por fah-VOR", "Choosing window seat"),
                    "zh" to at("请给我靠窗的座位。", "ching gay woh kow CHWAHNG duh dzwoh-way", "Choosing window seat"),
                    "hi" to at("कृपया विंडो सीट दीजिए।", "kri-puh-yaa WINDOW seat dee-ji-yeh", "Choosing window seat"),
                    "ar" to at("مقعد بجانب النافذة، من فضلك.", "maq-ad bi-jaa-ni-bi an-NAA-fi-dha min FAD-lik", "Choosing window seat")
                )
            ),
            LearningPhrase(
                id = "airport_9_boarding_pass",
                shortTitle = "Boarding pass & Gate 4",
                speakerRole = "Agent",
                translations = mapOf(
                    "en" to at("Here is your boarding pass. Your seat is 12A. You can board at Gate 4. Boarding starts at 10:30.", "heer iz yor BOR-ding pas ... seet 12A ... gayt 4 ... 10:30", "Key gate & time details"),
                    "fr" to at("Voici votre carte d'embarquement. Votre siège est le 12A. Embarquement porte 4 à 10h30.", "vwah-see voh-truh kart dahm-bark-mahn ... syezh 12A ... port 4 ah deez-uhr-trahnt", "Gate = porte, seat = siège"),
                    "es" to at("Aquí tiene su pase de abordar. Su asiento es el 12A. Puede abordar en la puerta 4 a las 10:30.", "ah-KEE TYEH-neh soo PAH-seh deh ah-bor-DAR ... ah-SYEHN-toh 12A ... PWER-tah 4", "Gate = puerta"),
                    "vi" to at("Đây là thẻ lên máy bay của bạn. Ghế 12A. Bạn lên máy bay ở cửa số 4 lúc 10:30.", "day lah teh len my BY ... geh 12A ... koo-uh soh BOHN look mu-uhy bah-mu-uhy", "Gate = cửa số"),
                    "th" to at("นี่คือบัตรขึ้นเครื่องค่ะ ที่นั่ง 12A ขึ้นเครื่องที่ประตู 4 เวลา 10:30 น. ค่ะ", "nee khue bat khuen KHRUEANG kha tee-nang 12A pra-too SEE way-laa sip mohng saam-sip", "Gate = ประตู (pra-too)"),
                    "ja" to at("こちらが搭乗券です。お席は12Aです。4番ゲートから10時30分に搭乗開始です。", "koh-chee-rah ga toh-joh-ken des ... 12A ... yon-ban geh-toh ... joo-jee sahn-jup-pun", "Gate = ゲート"),
                    "de" to at("Hier ist Ihre Bordkarte. Ihr Sitz ist 12A. Sie können an Gate 4 einsteigen. Boarding beginnt um 10:30 Uhr.", "heer ist EE-ruh BORT-kar-tuh ... zits 12A ... gate feer ... tsayn oor dry-sikh", "Boarding pass = Bordkarte"),
                    "it" to at("Ecco la sua carta d'imbarco. Il suo posto è 12A. Può imbarcarsi al gate 4 alle 10:30.", "EK-koh lah soo-ah KAR-tah deem-BAR-koh ... 12A ... gate KWAHT-troh", "Boarding pass = carta d'imbarco"),
                    "ko" to at("탑승권 여기 있습니다. 좌석은 12A입니다. 4번 게이트에서 10시 30분에 탑승합니다.", "tap-seung-gwon yeo-gi it-seum-ni-da ... 12A ... sa-beon ge-i-teu", "Boarding pass = 탑승권"),
                    "pt" to at("Aqui está seu cartão de embarque. Seu assento é 12A. Embarque no portão 4 às 10:30.", "ah-KEE ehs-TAH sew kar-TOWNG jee em-BAR-kee ... 12A ... por-TOWNG KWAH-troo", "Gate = portão"),
                    "zh" to at("这是您的登机牌。您的座位是12A。请在4号登机口登机，10点30分开始登机。", "juh shih neen-duh dung-jee-py ... 12A ... sih-how dung-jee-koh", "Boarding pass = 登机牌"),
                    "hi" to at("यह आपका बोर्डिंग पास है। आपकी सीट 12A है। आप गेट 4 से 10:30 बजे बोर्ड कर सकते हैं।", "yeh aap-kaa BOR-ding paas hai ... seat 12A ... gate 4 ... 10:30", "Key gate & seat details"),
                    "ar" to at("تفضل بطاقة الصعود. مقعدك هو 12A. يمكنك الصعود من البوابة 4 في الساعة 10:30.", "ta-fad-dal bi-TAA-qa-ta as-su-ood ... 12A ... al-baw-WAA-ba ar-ba-a", "Gate = البوابة")
                )
            ),
            LearningPhrase(
                id = "airport_10_thank_you_day",
                shortTitle = "Thank you, nice day!",
                speakerRole = "Traveler",
                translations = mapOf(
                    "en" to at("Thank you very much. Have a nice day!", "thank yoo VER-ee much hav uh nys day", "Polite closing thanks"),
                    "fr" to at("Merci beaucoup. Bonne journée !", "mehr-SEE boh-KOO bun zhoor-NAY", "Warm French farewell"),
                    "es" to at("Muchas gracias. ¡Que tenga un buen día!", "MOO-chahs GRAH-syahs keh TEN-gah oon bwehn DEE-ah", "Warm polite farewell"),
                    "vi" to at("Cảm ơn bạn rất nhiều. Chúc một ngày tốt lành!", "gahm uhn bahn zut NYEW chook moht ngai toht lahnh", "Warm grateful closing"),
                    "th" to at("ขอบคุณมากครับ ขอให้เป็นวันที่ดีนะครับ", "khop-khun MAAK khrap khaw hai pen wan tee DEE na khrap", "Grateful Thai closing"),
                    "ja" to at("どうもありがとうございます。良い一日を！", "doh-moh ah-ree-gah-toh go-zai-mas yoi ee-chee-nee-chee oh", "Warm polite farewell"),
                    "de" to at("Vielen Dank. Schönen Tag noch!", "FEE-len dahnk SHUR-nen tahk nokh", "Warm closing thanks"),
                    "it" to at("Grazie mille. Buona giornata!", "GRAHT-syeh MEEL-leh BWOH-nah jor-NAH-tah", "Warm Italian farewell"),
                    "ko" to at("대단히 감사합니다. 좋은 하루 보내세요!", "dae-dan-hi gam-sa-ham-ni-da jo-eun ha-ru bo-nae-se-yo", "Warm polite closing"),
                    "pt" to at("Muito obrigado. Tenha um bom dia!", "MWIN-too oh-bree-GAH-doo TEN-yah oom bohm JEE-ah", "Warm grateful closing"),
                    "zh" to at("非常感谢。祝您今天愉快！", "fay-chahng gahn-shyeh joo neen jin-tyen yoo-KWY", "Warm polite closing"),
                    "hi" to at("बहुत बहुत धन्यवाद। आपका दिन शुभ हो!", "buh-hut buh-hut dhun-yuh-vaad aap-kaa din shubh ho", "Warm polite closing"),
                    "ar" to at("شكراً جزيلاً. أتمنى لك يوماً سعيداً!", "shuk-ran ja-ZEE-lan a-ta-man-na la-ka yaw-man sa-ee-dan", "Warm polite closing")
                )
            ),
            LearningPhrase(
                id = "airport_11_good_flight",
                shortTitle = "Have a good flight!",
                speakerRole = "Agent",
                translations = mapOf(
                    "en" to at("You too. Have a good flight!", "yoo too hav uh good flyt", "Agent's warm send-off"),
                    "fr" to at("Vous aussi. Bon vol !", "voo oh-SEE bohn VOHL", "Bon vol = Have a good flight!"),
                    "es" to at("Igualmente. ¡Buen viaje!", "ee-gwal-MEN-teh BWEHN VYAH-heh", "Buen viaje = Have a nice trip!"),
                    "vi" to at("Bạn cũng vậy nhé. Chúc bạn có chuyến bay tốt đẹp!", "bahn koong vay nyeh chook bahn kaw chwen BY toht dep", "Warm flight send-off"),
                    "th" to at("เช่นกันค่ะ ขอให้เดินทางโดยสวัสดิภาพนะคะ", "chen GAN kha khaw hai dern-taang doy sa-wat-di-PHAAP na kha", "Traditional safe-journey wish"),
                    "ja" to at("こちらこそ。良い空の旅を！", "koh-chee-rah koh-soh yoi soh-rah no tah-bee oh", "Have a pleasant flight!"),
                    "de" to at("Ihnen auch. Guten Flug!", "EE-nen owkh GOO-ten flook", "Guten Flug = Good flight!"),
                    "it" to at("Anche a lei. Buon volo!", "AHN-keh ah lay bwon VOH-loh", "Buon volo = Good flight!"),
                    "ko" to at("감사합니다. 즐거운 비행 되세요!", "gam-sa-ham-ni-da jeul-geo-un bi-haeng doe-se-yo", "Have a pleasant flight!"),
                    "pt" to at("Para você também. Tenha um bom voo!", "PAH-rah voh-SEH tahm-BENG TEN-yah oom bohm VOH-oo", "Bom voo = Good flight!"),
                    "zh" to at("您也是。祝您旅途愉快！", "neen yeh shih joo neen lyoo-too yoo-KWY", "Have a pleasant journey!"),
                    "hi" to at("आपको भी। आपकी यात्रा मंगलमय हो!", "aap-ko bhee aap-kee YAA-traa man-gal-may ho", "Have a wonderful journey!"),
                    "ar" to at("وأنت أيضاً. رحلة سعيدة!", "wa AN-ta ay-dan rih-la sa-EE-da", "Have a pleasant flight!")
                )
            )
        )
    )
}
