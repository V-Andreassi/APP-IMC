package com.example.imc

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "imc_registro")
data class ImcRegistro(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var peso: Float,
    var altura: Float,
    var resultado: String,
    val data: String
)
