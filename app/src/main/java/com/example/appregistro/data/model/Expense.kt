package com.example.appregistro.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Expense(
    val id: Int? = null,
    val amount: Double,
    val category: String,
    val description: String,
    val title: String,
    val userId: Int? = null
) : Parcelable