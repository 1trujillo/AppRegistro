package com.example.appregistro.data.network

import com.example.appregistro.data.model.Expense
import retrofit2.Response
import retrofit2.http.*

interface ExpenseApiService {

    @GET("/api/expenses")
    suspend fun getExpenses(): List<Expense>

    @POST("/api/expenses")
    suspend fun createExpense(@Body expense: Expense): Response<Expense>

    @GET("/api/expenses/{id}")
    suspend fun getExpense(@Path("id") id: Long): Response<Expense>

    @PUT("/api/expenses/{id}")
    suspend fun updateExpense(
        @Path("id") id: Long,
        @Body expense: Expense
    ): Response<Expense>

    @DELETE("/api/expenses/{id}")
    suspend fun deleteExpense(@Path("id") id: Long): Response<Unit>
}
