package com.umg.practica1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Users (
   @PrimaryKey(autoGenerate = true) val id: Int = 0,
   val name: String,
   val address: String,
   val phone: String
)
