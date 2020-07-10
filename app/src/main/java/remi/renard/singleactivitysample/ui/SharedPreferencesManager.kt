package remi.renard.singleactivitysample.ui

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(private val sharedPreferences: SharedPreferences) {

    @Suppress("UNCHECKED_CAST")
    fun <T> getValue(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue) as T
            is Float -> sharedPreferences.getFloat(key, defaultValue) as T
            is Int -> sharedPreferences.getInt(key, defaultValue) as T
            is Long -> sharedPreferences.getLong(key, defaultValue) as T
            is String? -> sharedPreferences.getString(key, defaultValue) as T
            is Set<*> -> sharedPreferences.getStringSet(key, defaultValue as Set<String>) as T
            else -> throw IllegalArgumentException("Input type not supported.")
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> setValue(key: String, value: T) {
        when (value) {
            is Boolean -> sharedPreferences.edit { it.putBoolean(key, value) }
            is Float -> sharedPreferences.edit { it.putFloat(key, value) }
            is Int -> sharedPreferences.edit { it.putInt(key, value) }
            is Long -> sharedPreferences.edit { it.putLong(key, value) }
            is String -> sharedPreferences.edit { it.putString(key, value) }
            is Set<*> -> sharedPreferences.edit { it.putStringSet(key, value as Set<String>) }
            else -> throw IllegalArgumentException("Input type not supported.")
        }
    }

    fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }
}
