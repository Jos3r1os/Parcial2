package com.umg.practica1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductosDao {
    @Query("SELECT * FROM productos")
    fun getAllProductos(): Flow<List<Productos>>

    @Insert
    suspend fun insertProducto(producto: Productos)

    @Query("DELETE FROM productos")
    suspend fun deleteAllProductos()







}