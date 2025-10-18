package com.example.appregistro.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appregistro.R
import com.example.appregistro.data.UserDataStore
import com.example.appregistro.data.repository.AuthRepository
import com.example.appregistro.ui.login.LoginActivity
import com.example.appregistro.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<AuthViewModel> {
        val repo = AuthRepository(UserDataStore(this))
        object : androidx.lifecycle.ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return AuthViewModel(repo) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // Mostrar el nombre del usuario
        lifecycleScope.launch {
            val username = viewModel.getUsername() ?: "Usuario"
            tvWelcome.text = "¡Bienvenido, $username!"
        }

        btnLogout.setOnClickListener {
            viewModel.logout()
        }

        // Observar cambios en el estado de sesión
        lifecycleScope.launchWhenStarted {
            viewModel.isLoggedIn.collectLatest { loggedIn ->
                if (!loggedIn) {
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.checkSession()
    }
}
