package com.example.appregistro.data.repository

import com.example.appregistro.data.UserDataStore
import com.example.appregistro.data.model.*
import com.example.appregistro.data.network.AuthApiService
import com.example.appregistro.data.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository(
    private val dataStore: UserDataStore
) {

    private val api = RetrofitClient.instance.create(AuthApiService::class.java)

    suspend fun register(email: String, password: String): Boolean =
        withContext(Dispatchers.IO) {
            val response = api.register(RegisterRequest(email, password))
            return@withContext response.containsKey("message")
        }

    suspend fun login(email: String, password: String): Boolean =
        withContext(Dispatchers.IO) {
            val user = api.login(LoginRequest(email, password))

            dataStore.saveUser(user.id, user.email)

            return@withContext true
        }
}
