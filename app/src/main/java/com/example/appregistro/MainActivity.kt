package com.example.appregistro

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var btnAddExpense: Button
    private lateinit var btnCamera: ImageButton
    private lateinit var ivPhoto: ImageView
    private lateinit var adapter: SimpleAdapter
    private val data = mutableListOf<Map<String, String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        btnAddExpense = findViewById(R.id.btnAddExpense)
        btnCamera = findViewById(R.id.btnCamera)
        ivPhoto = findViewById(R.id.ivPhoto)

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
        btnAddExpense.setOnClickListener {
            val newExpense = mapOf("title" to "Nuevo gasto ${data.size + 1}", "amount" to "$0")
            data.add(newExpense)
            adapter.notifyDataSetChanged()
        }

        // ✏️ Editar gasto al hacer click
        listView.setOnItemClickListener { _, _, position, _ ->
            val expense = data[position]
            showEditDialog(position, expense)
        }

        // 📷 Abrir cámara usando Activity Result API
        val takePictureLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageBitmap = result.data?.extras?.get("data") as Bitmap
                ivPhoto.setImageBitmap(imageBitmap)
                ivPhoto.visibility = ImageView.VISIBLE
                Toast.makeText(this, "Foto tomada correctamente", Toast.LENGTH_SHORT).show()
            }
        }

        btnCamera.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePictureIntent.resolveActivity(packageManager) != null) {
                takePictureLauncher.launch(takePictureIntent)
            } else {
                Toast.makeText(this, "No se puede abrir la cámara", Toast.LENGTH_SHORT).show()
            }
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
            .setNeutralButton("Eliminar") { _, _ ->
                data.removeAt(position)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Gasto eliminado", Toast.LENGTH_SHORT).show()
            }
            .show()
    }
}
