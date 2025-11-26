package com.example.appregistro.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appregistro.databinding.ActivityDetailedNoteBinding

class DetailedNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailedNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombreGasto = intent.getStringExtra("nombreGasto") ?: ""
        val costoGasto = intent.getStringExtra("costoGasto") ?: ""

        binding.textNombreGasto.text = nombreGasto
        binding.textCostoGasto.text = costoGasto

        binding.btnGuardarDescripcion.setOnClickListener {

            val descripcion = binding.editDescripcion.text.toString().trim()
            if (descripcion.isEmpty()) {
                Toast.makeText(this, "Por favor, escribe una descripción", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Descripción guardada", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
