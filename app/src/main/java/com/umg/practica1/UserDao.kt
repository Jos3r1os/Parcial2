package com.umg.practica1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<Users>>

    @Insert
    suspend fun insertUser(user: Users)

    @Query("DELETE FROM users")
    suspend fun deleteAll()
}
