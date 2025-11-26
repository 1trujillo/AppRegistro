package com.example.appregistro.data.repository

import com.example.appregistro.data.model.Expense
import com.example.appregistro.data.remote.RetrofitClient

class ExpenseRepository {

    private val api = RetrofitClient.api

    suspend fun saveExpense(expense: Expense) = api.saveExpense(expense)
}
