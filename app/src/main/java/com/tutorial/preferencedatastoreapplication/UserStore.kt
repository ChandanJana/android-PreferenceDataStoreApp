package com.tutorial.preferencedatastoreapplication

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Chandan Jana on 30-08-2023.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */
class UserStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userToken")
        private val USER_TOKEN_KEY = stringPreferencesKey("user_token")
        private val INT_TOKEN_KEY = intPreferencesKey("int_token")
        private val BOOLEAN_TOKEN_KEY = booleanPreferencesKey("bool_token")
        private val FLOAT_TOKEN_KEY = floatPreferencesKey("float_token")
    }

    val getAccessToken: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_TOKEN_KEY] ?: ""
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = token
        }
    }
    suspend fun <T> saveToken(key: Preferences.Key<T>, token: T) {
        context.dataStore.edit { preferences ->
            preferences[key] = token
        }
    }
}