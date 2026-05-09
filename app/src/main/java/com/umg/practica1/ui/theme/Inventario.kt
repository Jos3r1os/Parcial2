package com.umg.practica1.ui.theme

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventario")
data class Inventario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val producto: String,
    val descripcion: String,
    val cantidad: Int,
    val precio: Double
)