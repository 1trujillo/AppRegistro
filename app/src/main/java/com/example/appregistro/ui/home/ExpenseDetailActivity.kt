package com.example.appregistro.ui.home

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.appregistro.R
import com.example.appregistro.data.model.Expense

class ExpenseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val expense = intent.getParcelableExtra<Expense>("expense") ?: return

        findViewById<TextView>(R.id.tvId).text = "ID: ${expense.id}"
        findViewById<TextView>(R.id.tvAmount).text = "Monto: $ ${expense.amount}"
        findViewById<TextView>(R.id.tvCategory).text = "Categoría: ${expense.category}"
        findViewById<TextView>(R.id.tvDescription).text = "Descripción: ${expense.description}"
    }
}
