package com.example.appregistro.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.appregistro.R

class ExpenseDetailActivity : AppCompatActivity() {

    private lateinit var editNombreGasto: EditText
    private lateinit var editCostoGasto: EditText
    private lateinit var btnGuardar: Button
    private lateinit var btnCancelar: Button
    private lateinit var btnAgregarDescripcion: Button

    private var descripcionLarga: String = ""
    private var expensePosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_detail)

        editNombreGasto = findViewById(R.id.editNombreGasto)
        editCostoGasto = findViewById(R.id.editCostoGasto)
        btnGuardar = findViewById(R.id.btnGuardar)
        btnCancelar = findViewById(R.id.btnCancelar)
        btnAgregarDescripcion = findViewById(R.id.btnAgregarDescripcion)

        // Recibir datos del gasto
        intent?.let {
            val nombre = it.getStringExtra("nombre")
            val costo = it.getStringExtra("costo")
            expensePosition = it.getIntExtra("position", -1)

            nombre?.let { n -> editNombreGasto.setText(n) }
            costo?.let { c -> editCostoGasto.setText(c) }
        }

        // Botón para abrir la pantalla de descripción larga
        btnAgregarDescripcion.setOnClickListener {
            val intentDesc = Intent(this, DescriptionActivity::class.java)
            intentDesc.putExtra("descripcion_actual", descripcionLarga)
            startActivityForResult(intentDesc, 1001)
        }

        // Botón Guardar
        btnGuardar.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("nombre", editNombreGasto.text.toString())
                putExtra("costo", editCostoGasto.text.toString())
                putExtra("descripcion", descripcionLarga)
                putExtra("position", expensePosition)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        // Botón Cancelar
        btnCancelar.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    // Recibir la descripción larga desde DescriptionActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001 && resultCode == RESULT_OK) {
            descripcionLarga = data?.getStringExtra("descripcion") ?: ""
        }
    }
}
