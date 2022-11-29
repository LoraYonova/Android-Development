package com.example.myapplication.db.dao

import androidx.room.*
import com.example.myapplication.db.entity.CocktailEntity

@Dao
interface CocktailDao {

    @Query("SELECT * FROM cocktails_lora_db")
    suspend fun getCocktails(): List<CocktailEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(cocktails: List<CocktailEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(cocktail: CocktailEntity)
}