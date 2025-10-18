package com.example.appregistro.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.Lifecycle
import com.example.appregistro.data.UserDataStore
import com.example.appregistro.data.repository.AuthRepository
import com.example.appregistro.databinding.ActivityLoginBinding
import com.example.appregistro.viewmodel.LoginViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    // ✅ ViewModel con Factory personalizada
    private val loginViewModel by viewModels<LoginViewModel> {
        val repo = AuthRepository(UserDataStore(this))
        object : androidx.lifecycle.ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return LoginViewModel(repo) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Observa el estado del login usando repeatOnLifecycle (no deprecated)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginSuccess.collectLatest { success ->
                    success?.let {
                        if (it) {
                            Toast.makeText(this@LoginActivity, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                            // TODO: navegar a MainActivity
                        } else {
                            Toast.makeText(this@LoginActivity, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        // ✅ Botón de login
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            loginViewModel.login(email, password)
        }
        // ✅ Ir a pantalla de registro
        binding.tvGoToRegister.setOnClickListener {
            val intent = Intent(this, com.example.appregistro.ui.register.RegisterActivity::class.java)
            startActivity(intent)
        }

    }

}
