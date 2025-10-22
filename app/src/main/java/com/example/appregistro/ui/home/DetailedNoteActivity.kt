package com.example.appregistro.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appregistro.data.model.Expense
import com.example.appregistro.databinding.ActivityDetailedNoteBinding

class aDetailedNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailedNoteBinding
    private var currentExpense: Expense? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentExpense = intent.getParcelableExtra("expense")

        // Si viene el expense, llenar campos
        currentExpense?.let { e ->
            binding.tvNoteTitle.text = "Editar: ${e.title}"
            binding.etLongDescription.setText(e.description)
            // Si tu Expense llevará referencia a imagen en el futuro, aquí la cargarías en ivPhoto
        }

        binding.btnTakePhoto.setOnClickListener {
            // Por ahora solo mostramos un Toast. Más adelante implementaremos la cámara.
            Toast.makeText(this, "Función de cámara pendiente de implementar", Toast.LENGTH_SHORT).show()
        }

        binding.btnSaveLongDescription.setOnClickListener {
            val newLongDesc = binding.etLongDescription.text.toString().trim()
            if (currentExpense != null) {
                // Aquí podrías actualizar el objeto y persistirlo (DB, lista, o pasar resultados)
                // Por ahora solo informamos y cerramos.
                Toast.makeText(this, "Descripción guardada", Toast.LENGTH_SHORT).show()
                // Si quieres devolver cambios a la activity anterior, usa setResult con un Intent.
                finish()
            } else {
                Toast.makeText(this, "No se encontró el gasto", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
