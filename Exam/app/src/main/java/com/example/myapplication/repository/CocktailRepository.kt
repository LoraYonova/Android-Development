package com.example.myapplication.repository

import android.content.Context
import com.example.myapplication.db.dao.CocktailDao
import com.example.myapplication.db.entity.CocktailEntity
import com.example.myapplication.model.CocktailListResponse
import com.example.myapplication.service.CocktailService
import com.example.myapplication.util.NetworkUtil

class CocktailRepository constructor(
    private val context: Context,
    private val cocktailService: CocktailService,
    private val cocktailDao: CocktailDao
) {

    suspend fun getCocktails() : List<CocktailEntity> {
        return try {
            if (NetworkUtil.isConnected(context)) {

                val cocktail = cocktailService.getCocktails().execute().body()
                val roomCocktail = cocktail?.map { mapResponseToDbModel(it) }
                cocktailDao.insertAll(roomCocktail ?: return arrayListOf())
            }

            cocktailDao.getCocktails()
        } catch (e: Exception) {
            println(e.message)
            arrayListOf()
        }
    }

    suspend fun updateCocktail(cocktail: CocktailEntity) {
        cocktailDao.update(cocktail)
    }

    private fun mapResponseToDbModel(response: CocktailListResponse): CocktailEntity {
        return CocktailEntity(

            srtDrink = response.drinks[0].strDrink,
            strDrinkThumb = response.drinks[1].strDrinkThumb,
            idDrink = response.drinks[2].idDrink,
            favourite = false
        )
    }
}