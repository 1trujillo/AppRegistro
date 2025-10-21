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
import com.example.appregistro.ui.register.RegisterActivity
import com.example.appregistro.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    // ✅ ViewModel con Factory personalizada
    private val loginViewModel by viewModels<LoginViewModel> {
        val repo = AuthRepository(UserDataStore(this))
        object : androidx.lifecycle.ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return LoginViewModel(repo) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Observa el estado del login usando repeatOnLifecycle
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginSuccess.collectLatest { success ->
                    when (success) {
                        true -> {
                            Toast.makeText(this@LoginActivity, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                            // Navegar a MainActivity
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish() // para no volver al login
                        }
                        false -> {
                            Toast.makeText(this@LoginActivity, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
                        }
                        null -> {
                            // No hacer nada aún
                        }
                    }
                }
            }
        }

        // ✅ Botón de login
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty()) {
                binding.etEmail.error = "El correo es obligatorio"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                binding.etPassword.error = "La contraseña es obligatoria"
                return@setOnClickListener
            }

            loginViewModel.login(email, password)
        }

        // ✅ Ir a pantalla de registro
        binding.tvGoToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
