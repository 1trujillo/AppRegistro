package com.example.appregistro.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080") // IMPORTANTE: para emulador Android
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val api: ExpenseApiService = retrofit.create(ExpenseApiService::class.java)
}
