package com.example.appregistro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appregistro.data.repository.AuthRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _loginSuccess = MutableStateFlow<Boolean?>(null)
    val loginSuccess: StateFlow<Boolean?> = _loginSuccess

    // CAMBIO: Renombrado de 'username' a 'email' para claridad y coincidencia con el diseño
    fun login(email: String, password: String) {
        viewModelScope.launch {
            // El repositorio necesita ser implementado para funcionar.
            // Esto solo es un placeholder ya que AuthRepository no está en el contexto.
            // Supongamos que AuthRepository tiene la función login(email, password)
            val success = repository.login(email, password)
            _loginSuccess.value = success
            if (success) repository.setLoggedIn(true)
        }
    }
}
