package com.example.appregistro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appregistro.data.repository.AuthRepository

/**
 * Factory para instanciar el LoginViewModel con el repositorio inyectado.
 * Esto resuelve el error de 'no se puede crear el ViewModel con parámetros'.
 */
class LoginViewModelFactory(private val repository: AuthRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            // Retorna una nueva instancia de LoginViewModel, pasándole la dependencia.
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
