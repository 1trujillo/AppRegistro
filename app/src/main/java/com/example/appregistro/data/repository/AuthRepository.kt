// Archivo: AuthRepository.kt
package com.example.appregistro.data.repository

import com.example.appregistro.data.UserDataStore
import kotlinx.coroutines.flow.firstOrNull

class AuthRepository(private val userDataStore: UserDataStore) {

    suspend fun register(username: String, password: String) {
        userDataStore.saveUser(username, password)
    }

    suspend fun login(username: String, password: String): Boolean {
        val savedUser = userDataStore.getUsername().firstOrNull()
        val savedPass = userDataStore.getPassword().firstOrNull()
        return username == savedUser && password == savedPass
    }

    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        userDataStore.setLoggedIn(isLoggedIn)
    }

    suspend fun isLoggedIn(): Boolean {
        return userDataStore.isLoggedIn().firstOrNull() ?: false
    }

    suspend fun getUsername(): String? {
        return userDataStore.getUsername().firstOrNull()
    }
}
