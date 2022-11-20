package com.example.myapplication.db.dao

import androidx.room.*
import com.example.myapplication.db.entity.CryptoDetails

@Dao
interface CryptoDao {

    @Query("SELECT * FROM crypto_lora")
    suspend fun getCrypto(): List<CryptoDetails>

    @Query("SELECT * FROM crypto_lora WHERE id=:id")
    suspend fun getCryptoById(id: String): CryptoDetails

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(crypto: List<CryptoDetails>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(crypto: CryptoDetails)


}