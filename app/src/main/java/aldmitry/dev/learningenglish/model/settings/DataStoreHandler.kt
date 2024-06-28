package aldmitry.dev.learningenglish.model.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

// Инициализация DataStoreHandler бд
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store") // "data_store" - имя таблицы

class DataStoreHandler(private val context: Context) {
    // Функция сохраняет настройки из объекта SettingsData в бд
    suspend fun saveSettings(settingsData: SettingsData) {
        context.dataStore.edit {
            it[booleanPreferencesKey("repeat_lesson")] = settingsData.repeatWrongLesson
            it[longPreferencesKey("answer_show_time")] = settingsData.answerShowTime
        }
    }

    // Функция возвращает объект SettingsData
    fun getSettings() = context.dataStore.data.map {
        return@map SettingsData(
            it[booleanPreferencesKey("repeat_lesson")] ?: true,
            it[longPreferencesKey("answer_show_time")] ?: 2
        )
    }

}