package com.example.appregistro.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appregistro.R
import com.example.appregistro.data.model.Expense

class RegisterExpenseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_expense)

        val etAmount = findViewById<EditText>(R.id.etAmount)
        val etCategory = findViewById<EditText>(R.id.etCategory)
        val etDescription = findViewById<EditText>(R.id.etDescription)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            val amountStr = etAmount.text.toString().trim()
            val category = etCategory.text.toString().trim()
            val description = etDescription.text.toString().trim()

            if (amountStr.isEmpty() || category.isEmpty()) {
                Toast.makeText(this, "Completa al menos monto y categoría", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountStr.toDoubleOrNull()
            if (amount == null) {
                Toast.makeText(this, "Monto inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val expense = Expense(amount = amount, category = category, description = description)
            val resultIntent = Intent().putExtra("expense", expense)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
