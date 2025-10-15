// Archivo: AuthViewModel.kt
package com.example.appregistro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appregistro.data.model.Usuario
import com.example.appregistro.data.repository.AuthRepository

class AuthViewModel : ViewModel() {

    private val authRepository = AuthRepository()

    // LiveData para observar el estado del login
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    // Función que la View llamará para iniciar sesión
    fun login(email: String, password: String) {
        val result = authRepository.login(email, password)
        _loginResult.value = result
    }

    // Función para el registro
    fun register(usuario: Usuario) {
        // Lógica de registro...
    }
}