package com.example.appregistro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appregistro.data.repository.AuthRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val repo: AuthRepository) : ViewModel() {

    fun register(email: String, password: String, onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            val ok = repo.register(email, password)
            if (ok) onSuccess() else onError()
        }
    }
}
