package com.example.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.model.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "polyglot_master_prefs")

class UserPreferencesRepository(private val context: Context) {

    private object Keys {
        val USERNAME = stringPreferencesKey("username")
        val NATIVE_LANGUAGE = stringPreferencesKey("native_language")
        val TARGET_LANGUAGES = stringPreferencesKey("target_languages_ordered")
        val XP = intPreferencesKey("xp")
        val STREAK_DAYS = intPreferencesKey("streak_days")
        val HEARD_KEYS = stringSetPreferencesKey("heard_phrase_keys")
        val COMPLETED_QUIZZES = stringSetPreferencesKey("completed_quiz_units")
        val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
    }

    val userProfileFlow: Flow<UserProfile> = context.dataStore.data.map { prefs ->
        val rawTargets = prefs[Keys.TARGET_LANGUAGES].orEmpty()
        val targets = rawTargets
            .split(",")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .take(3)

        UserProfile(
            username = prefs[Keys.USERNAME].orEmpty(),
            nativeLanguageCode = prefs[Keys.NATIVE_LANGUAGE] ?: "en",
            targetLanguageCodes = targets,
            xp = prefs[Keys.XP] ?: 0,
            streakDays = prefs[Keys.STREAK_DAYS] ?: 1,
            heardPhraseKeys = prefs[Keys.HEARD_KEYS] ?: emptySet(),
            completedQuizUnitIds = prefs[Keys.COMPLETED_QUIZZES] ?: emptySet(),
            onboardingCompleted = prefs[Keys.ONBOARDING_COMPLETED] ?: false
        )
    }

    suspend fun saveOnboardingProfile(
        username: String,
        nativeLanguageCode: String,
        targetLanguageCodes: List<String>
    ) {
        context.dataStore.edit { prefs ->
            prefs[Keys.USERNAME] = username.trim()
            prefs[Keys.NATIVE_LANGUAGE] = nativeLanguageCode
            prefs[Keys.TARGET_LANGUAGES] = targetLanguageCodes.distinct().take(3).joinToString(",")
            prefs[Keys.ONBOARDING_COMPLETED] = true
            if ((prefs[Keys.STREAK_DAYS] ?: 0) == 0) {
                prefs[Keys.STREAK_DAYS] = 1
            }
        }
    }

    suspend fun updateLanguages(
        nativeLanguageCode: String,
        targetLanguageCodes: List<String>
    ) {
        context.dataStore.edit { prefs ->
            prefs[Keys.NATIVE_LANGUAGE] = nativeLanguageCode
            prefs[Keys.TARGET_LANGUAGES] = targetLanguageCodes.distinct().take(3).joinToString(",")
        }
    }

    suspend fun recordPhraseHeard(phraseKey: String, xpBonus: Int = 5) {
        context.dataStore.edit { prefs ->
            val currentHeard = prefs[Keys.HEARD_KEYS] ?: emptySet()
            if (!currentHeard.contains(phraseKey)) {
                prefs[Keys.HEARD_KEYS] = currentHeard + phraseKey
                prefs[Keys.XP] = (prefs[Keys.XP] ?: 0) + xpBonus
            }
        }
    }

    suspend fun addQuizXp(unitId: String?, xpEarned: Int) {
        context.dataStore.edit { prefs ->
            prefs[Keys.XP] = (prefs[Keys.XP] ?: 0) + xpEarned
            if (unitId != null) {
                val completed = prefs[Keys.COMPLETED_QUIZZES] ?: emptySet()
                prefs[Keys.COMPLETED_QUIZZES] = completed + unitId
            }
        }
    }

    suspend fun resetOnboarding() {
        context.dataStore.edit { prefs ->
            prefs[Keys.ONBOARDING_COMPLETED] = false
        }
    }
}
