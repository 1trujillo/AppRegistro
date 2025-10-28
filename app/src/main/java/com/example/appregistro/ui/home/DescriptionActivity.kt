package com.example.appregistro.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appregistro.databinding.ActivityDescriptionBinding

class DescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombreGasto = intent.getStringExtra("nombreGasto")
        val costoGasto = intent.getStringExtra("costoGasto")

        binding.textDescripcion.text = "Gasto: $nombreGasto\nCosto: $costoGasto"
    }
}
