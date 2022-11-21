package com.example.myapplication.repository

import android.content.Context
import com.example.myapplication.db.dao.MakeupDao
import com.example.myapplication.db.entity.MakeupDetails
import com.example.myapplication.model.MakeupResponse
import com.example.myapplication.service.MakeupService
import com.example.myapplication.util.NetworkUtil

class MakeupRepository constructor(
    private val context: Context,
    private val makeupService: MakeupService,
    private val makeupDao: MakeupDao
) {

    suspend fun getMakeup(): List<MakeupDetails> {
        return try {
            if (NetworkUtil.isConnected(context)) {
                val makeup = makeupService.getMakeup().execute().body()
                val roomMakeup = makeup?.map { mapResponseToDbModel(it) }
                makeupDao.insertAll(roomMakeup ?: return arrayListOf())
            }
            makeupDao.getMakeup()
        } catch (e: Exception) {
            arrayListOf()
        }
    }

    suspend fun getMakeupById(id: Int): MakeupDetails? {
        return try {
            makeupDao.getMakeupById(id)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun updateMakeup(makeup: MakeupDetails) {
        makeupDao.update(makeup)
    }

    private fun mapResponseToDbModel(response: MakeupResponse): MakeupDetails {
        return MakeupDetails(
            id = response.id,
            imageLink = response.image_link,
            name = response.name,
            brand = response.brand,
            price = response.price,
            rating = response.rating,
            productType = response.product_type,
            description = response.description,
            favourite = false
        )
    }

}