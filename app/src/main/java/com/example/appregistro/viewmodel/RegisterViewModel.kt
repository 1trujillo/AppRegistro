package com.example.appregistro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appregistro.data.repository.AuthRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: AuthRepository) : ViewModel() {

    fun register(username: String, password: String, onRegistered: () -> Unit) {
        viewModelScope.launch {
            repository.register(username, password)
            onRegistered()
        }
    }
}
