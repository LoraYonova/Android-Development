package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid=:id")
    suspend fun getUserById(id: Int): User

    @Insert
    suspend fun insert(vararg :User)

//    @Insert
//    suspend fun insertAll(vararg user: User)
//
//    @Delete
//    suspend fun delete(user: User)
}