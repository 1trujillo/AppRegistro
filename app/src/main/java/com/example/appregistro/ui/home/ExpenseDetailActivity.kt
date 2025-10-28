package com.example.appregistro.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appregistro.databinding.ActivityExpenseDetailBinding

class ExpenseDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExpenseDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpenseDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Botón Cancelar
        binding.btnCancelar.setOnClickListener {
            finish()
        }

        // Botón Guardar
        binding.btnGuardar.setOnClickListener {
            val nombreGasto = binding.editNombreGasto.text.toString().trim()
            val costoGasto = binding.editCostoGasto.text.toString().trim()

            if (nombreGasto.isEmpty() || costoGasto.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Gasto guardado correctamente", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        // Botón Agregar Descripción → abre otra pantalla
        binding.btnAgregarDescripcion.setOnClickListener {
            val nombreGasto = binding.editNombreGasto.text.toString().trim()
            val costoGasto = binding.editCostoGasto.text.toString().trim()

            val intent = Intent(this, DetailedNoteActivity::class.java).apply {
                putExtra("nombreGasto", nombreGasto)
                putExtra("costoGasto", costoGasto)
            }
            startActivity(intent)
        }
    }
}
