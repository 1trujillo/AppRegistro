package com.example.appregistro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appregistro.data.model.Expense
import com.example.appregistro.data.repository.ExpenseRepository
import kotlinx.coroutines.launch

class ExpenseViewModel(private val repo: ExpenseRepository) : ViewModel() {

    fun saveExpense(expense: Expense, onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            val response = repo.saveExpense(expense)
            if (response.isSuccessful) {
                onSuccess()
            } else {
                onError()
            }
        }
    }
}
