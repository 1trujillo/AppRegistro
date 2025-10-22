package com.example.appregistro

import android.app.AlertDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var addButton: Button
    private lateinit var adapter: SimpleAdapter
    private val data = mutableListOf<Map<String, String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        addButton = findViewById(R.id.btnAddExpense)

        // Datos de ejemplo
        data.addAll(
            listOf(
                mapOf("title" to "Supermercado", "amount" to "$20"),
                mapOf("title" to "Gasolina", "amount" to "$35"),
                mapOf("title" to "Netflix", "amount" to "$10")
            )
        )

        val from = arrayOf("title", "amount")
        val to = intArrayOf(R.id.tvTitle, R.id.tvAmount)
        adapter = SimpleAdapter(this, data, R.layout.item_expense, from, to)
        listView.adapter = adapter

        // ➕ Agregar nuevo gasto
        addButton.setOnClickListener {
            val newExpense = mapOf("title" to "Nuevo gasto ${data.size + 1}", "amount" to "$0")
            data.add(newExpense)
            adapter.notifyDataSetChanged()
        }

        // ✏️ Editar gasto al hacer click
        listView.setOnItemClickListener { _, _, position, _ ->
            val expense = data[position]
            showEditDialog(position, expense)
        }
    }

    private fun showEditDialog(position: Int, expense: Map<String, String>) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_expense, null)
        val etTitle = dialogView.findViewById<EditText>(R.id.etTitle)
        val etAmount = dialogView.findViewById<EditText>(R.id.etAmount)

        etTitle.setText(expense["title"])
        etAmount.setText(expense["amount"])

        AlertDialog.Builder(this)
            .setTitle("Editar gasto")
            .setView(dialogView)
            .setPositiveButton("Guardar") { _, _ ->
                val newTitle = etTitle.text.toString()
                val newAmount = etAmount.text.toString()

                data[position] = mapOf("title" to newTitle, "amount" to newAmount)
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}
