package com.example.myapplication.db.dao

import androidx.room.*
import com.example.myapplication.db.entity.MakeupDetails

@Dao
interface MakeupDao {

    @Query("SELECT * FROM makeup_db")
    suspend fun getMakeup(): List<MakeupDetails>

    @Query("SELECT * FROM makeup_db WHERE id=:id")
    suspend fun getMakeupById(id: Int): MakeupDetails

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(makeup: List<MakeupDetails>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(crypto: MakeupDetails)
}