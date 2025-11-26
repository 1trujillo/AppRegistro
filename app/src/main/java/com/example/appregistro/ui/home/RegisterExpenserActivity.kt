package com.example.appregistro.ui.home

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.appregistro.R
import com.example.appregistro.data.model.Expense
import com.example.appregistro.data.repository.ExpenseRepository
import com.example.appregistro.viewmodel.ExpenseViewModel

class RegisterExpenseActivity : AppCompatActivity() {

    private val viewModel by viewModels<ExpenseViewModel> {
        val repo = ExpenseRepository()
        object : androidx.lifecycle.ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return ExpenseViewModel(repo) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_expense)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etAmount = findViewById<EditText>(R.id.etAmount)
        val etCategory = findViewById<EditText>(R.id.etCategory)
        val etDescription = findViewById<EditText>(R.id.etDescription)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val amountStr = etAmount.text.toString().trim()
            val category = etCategory.text.toString().trim()
            val description = etDescription.text.toString().trim()

            if (title.isEmpty() || amountStr.isEmpty() || category.isEmpty()) {
                Toast.makeText(this, "Completa título, monto y categoría", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountStr.toDoubleOrNull()
            if (amount == null) {
                Toast.makeText(this, "Monto inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val expense = Expense(
                title = title,
                amount = amount,
                category = category,
                description = description,
                userId = 1 // <- temporal, luego lo obtendremos del login
            )

            viewModel.saveExpense(
                expense,
                onSuccess = {
                    Toast.makeText(this, "Gasto guardado correctamente", Toast.LENGTH_SHORT).show()
                    finish()
                },
                onError = {
                    Toast.makeText(this, "Error al guardar gasto", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}
