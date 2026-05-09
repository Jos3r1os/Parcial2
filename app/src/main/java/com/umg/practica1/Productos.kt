package com.umg.practica1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class Productos(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val producto: String,
    val descripcion: String,
    val cantidad: Int,
    val precio: Double,
    val categoria: String,
    val stockInicial: Int,
    val stockMinimo: Int
)
