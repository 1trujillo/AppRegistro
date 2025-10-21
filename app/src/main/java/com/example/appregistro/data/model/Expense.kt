package com.example.appregistro.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Expense(
    val id: Int = (0..100000).random(),
    val amount: Double,
    val category: String,
    val description: String
) : Parcelable
