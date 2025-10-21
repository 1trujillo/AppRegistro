package com.example.appregistro

import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)

        val data = listOf(
            mapOf("title" to "Compra supermercado", "amount" to "$20", "desc" to "Frutas y verduras"),
            mapOf("title" to "Gasolina", "amount" to "$35", "desc" to "Tanque completo"),
            mapOf("title" to "Netflix", "amount" to "$10", "desc" to "Suscripción mensual")
        )

        val from = arrayOf("title", "amount", "desc")
        val to = intArrayOf(R.id.tvTitle, R.id.tvAmount, R.id.tvDesc)

        val adapter = SimpleAdapter(this, data, R.layout.item_expense, from, to)
        listView.adapter = adapter
    }
}
