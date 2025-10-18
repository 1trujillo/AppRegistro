package com.example.appregistro.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserDataStore(private val context: Context) {

    companion object {
        private val USERNAME_KEY = stringPreferencesKey("username")
        private val PASSWORD_KEY = stringPreferencesKey("password")
        private val LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
    }

    suspend fun saveUser(username: String, password: String) {
        context.dataStore.edit { prefs ->
            prefs[USERNAME_KEY] = username
            prefs[PASSWORD_KEY] = password
        }
    }

    fun getUsername(): Flow<String?> = context.dataStore.data.map { it[USERNAME_KEY] }
    fun getPassword(): Flow<String?> = context.dataStore.data.map { it[PASSWORD_KEY] }

    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[LOGGED_IN_KEY] = isLoggedIn
        }
    }

    fun isLoggedIn(): Flow<Boolean> = context.dataStore.data.map { it[LOGGED_IN_KEY] ?: false }
}
