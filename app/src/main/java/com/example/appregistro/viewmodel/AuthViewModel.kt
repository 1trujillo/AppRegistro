package com.example.appregistro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appregistro.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _username = MutableStateFlow<String?>(null)
    val username: StateFlow<String?> = _username

    init {
        // Cargar estado inicial
        checkSession()
        loadUsername()
    }

    fun checkSession() {
        viewModelScope.launch {
            _isLoggedIn.value = repository.isLoggedIn()
        }
    }

    private fun loadUsername() {
        viewModelScope.launch {
            _username.value = repository.getUsername()
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.setLoggedIn(false)
            _isLoggedIn.value = false
        }
    }

    suspend fun getUsername(): String? {
        return repository.getUsername()
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val success = repository.login(username, password)
            _isLoggedIn.value = success
            if (success) {
                _username.value = username
            }
        }
    }

    fun register(username: String, password: String) {
        viewModelScope.launch {
            repository.register(username, password)
            _username.value = username
            _isLoggedIn.value = true
            repository.setLoggedIn(true)
        }
    }
}
