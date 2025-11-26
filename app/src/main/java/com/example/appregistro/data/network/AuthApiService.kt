package com.example.appregistro.data.network

import com.example.appregistro.data.model.LoginRequest
import com.example.appregistro.data.model.RegisterRequest
import com.example.appregistro.data.model.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("auth/register")
    suspend fun register(@Body req: RegisterRequest): Map<String, String>

    @POST("auth/login")
    suspend fun login(@Body req: LoginRequest): UserResponse
}
