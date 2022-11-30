package com.example.myapplication.repository

import android.content.Context
import com.example.myapplication.db.dao.CocktailDao
import com.example.myapplication.db.entity.CocktailDetailEntity
import com.example.myapplication.db.entity.CocktailEntity
import com.example.myapplication.model.CocktailDetailResponse
import com.example.myapplication.model.CocktailListResponse
import com.example.myapplication.model.Drink
import com.example.myapplication.model.Drink2
import com.example.myapplication.service.CocktailService
import com.example.myapplication.util.NetworkUtil

class CocktailRepository constructor(
    private val context: Context,
    private val cocktailService: CocktailService,
    private val cocktailDao: CocktailDao
) {

    suspend fun getCocktails(): List<CocktailEntity> {
        return try {
            if (NetworkUtil.isConnected(context)) {

                val cocktail = cocktailService.getCocktails().execute().body()

                val roomCocktail = cocktail?.drinks?.map { mapResponseToDbModel(it) }
                cocktailDao.insertAll(roomCocktail ?: return arrayListOf())
            }

            cocktailDao.getCocktails()
        } catch (e: Exception) {
            arrayListOf()
        }
    }

    suspend fun getCocktailById(id: String): CocktailDetailEntity? {
        return try {

            if (NetworkUtil.isConnected(context)) {
                val cocktail = cocktailService.getCocktailById(id).execute().body()
                val roomCocktail = cocktail?.drinks?.map { mapDetailResponseToDbModel(it) }
                cocktailDao.insertAllDetails(roomCocktail ?: return null)
            }
            return cocktailDao.getCocktailById(id)

        } catch (e: Exception) {
            println(e.message)
            null
        }
    }

    private fun mapDetailResponseToDbModel(response: Drink): CocktailDetailEntity {
        return CocktailDetailEntity(
            idDrink = response.idDrink,
            srtDrink = response.strDrink,
            strDrinkThumb = response.strDrinkThumb,
            strGlass = response.strGlass,
            strIngredient1 = response.strIngredient1,
            strMeasure1 = response.strMeasure1,
            strInstructions = response.strInstructions
        )
    }

    suspend fun updateCocktail(cocktail: CocktailEntity) {
        cocktailDao.update(cocktail)
    }

    private fun mapResponseToDbModel(response: Drink): CocktailEntity {
        return CocktailEntity(

            srtDrink = response.strDrink,
            strDrinkThumb = response.strDrinkThumb,
            idDrink = response.idDrink,
            favourite = false
        )
    }
}