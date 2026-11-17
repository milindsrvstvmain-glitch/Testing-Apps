package com.example.audio

import android.content.Context
import android.content.Intent
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.speech.tts.Voice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale
import java.util.UUID

sealed class TtsAvailability {
    data object Initializing : TtsAvailability()
    data object Ready : TtsAvailability()
    data object Unavailable : TtsAvailability()
}

class NativeSpeechManager(context: Context) {

    private val appContext = context.applicationContext
    private var tts: TextToSpeech? = null

    private val _availability = MutableStateFlow<TtsAvailability>(TtsAvailability.Initializing)
    val availability: StateFlow<TtsAvailability> = _availability.asStateFlow()

    private val _speakingKey = MutableStateFlow<String?>(null)
    val speakingKey: StateFlow<String?> = _speakingKey.asStateFlow()

    private val _voiceWarning = MutableStateFlow<String?>(null)
    val voiceWarning: StateFlow<String?> = _voiceWarning.asStateFlow()

    init {
        tts = TextToSpeech(appContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                _availability.value = TtsAvailability.Ready
                tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                    override fun onStart(utteranceId: String?) {
                        _speakingKey.value = utteranceId?.substringBefore("::")
                    }

                    override fun onDone(utteranceId: String?) {
                        val key = utteranceId?.substringBefore("::")
                        if (_speakingKey.value == key) {
                            _speakingKey.value = null
                        }
                    }

                    @Deprecated("Deprecated in Java")
                    override fun onError(utteranceId: String?) {
                        _speakingKey.value = null
                    }

                    override fun onError(utteranceId: String?, errorCode: Int) {
                        _speakingKey.value = null
                    }
                })
            } else {
                _availability.value = TtsAvailability.Unavailable
            }
        }
    }

    fun speak(
        key: String,
        text: String,
        localeTag: String,
        languageName: String,
        slow: Boolean = false
    ) {
        val engine = tts
        if (engine == null || _availability.value != TtsAvailability.Ready) {
            _voiceWarning.value = "Speech engine is still warming up. Please try again in a moment."
            return
        }

        val targetLocale = Locale.forLanguageTag(localeTag)
        val langResult = engine.setLanguage(targetLocale)

        // Prefer a high-quality native voice matching the exact language if available
        val bestVoice: Voice? = runCatching {
            engine.voices
                ?.filter { voice ->
                    voice.locale.language.equals(targetLocale.language, ignoreCase = true) &&
                        !voice.isNetworkConnectionRequired
                }
                ?.maxByOrNull { voice ->
                    val exactCountryScore = if (voice.locale.country.equals(targetLocale.country, ignoreCase = true)) 10 else 0
                    val qualityScore = voice.quality / 100
                    exactCountryScore + qualityScore
                }
                ?: engine.voices?.firstOrNull { voice ->
                    voice.locale.language.equals(targetLocale.language, ignoreCase = true)
                }
        }.getOrNull()

        if (bestVoice != null) {
            runCatching { engine.voice = bestVoice }
        }

        if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
            _voiceWarning.value =
                "$languageName offline voice pack isn't installed on this device yet. Tap 'Voice Setup' to install it."
        } else {
            _voiceWarning.value = null
        }

        engine.setSpeechRate(if (slow) 0.68f else 0.96f)
        engine.setPitch(1.0f)

        val utteranceId = "$key::${UUID.randomUUID()}"
        _speakingKey.value = key
        engine.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
    }

    fun clearWarning() {
        _voiceWarning.value = null
    }

    fun buildInstallVoiceIntent(): Intent = Intent(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    fun stop() {
        tts?.stop()
        _speakingKey.value = null
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
    }
}
