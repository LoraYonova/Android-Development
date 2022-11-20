package com.example.myapplication.repository

import android.content.Context
import android.util.Log
import com.example.myapplication.db.dao.CryptoDao
import com.example.myapplication.db.entity.CryptoDetails
import com.example.myapplication.model.CryptoResponseDetails
import com.example.myapplication.service.CryptoService
import com.example.myapplication.util.NetworkUtil

class CryptoRepository constructor(
    private val context: Context,
    private val cryptoService: CryptoService,
    private val cryptoDao: CryptoDao
) {

    suspend fun getCrypto(currencyCode: String = "usd"): List<CryptoDetails> {

        return try {
            if (NetworkUtil.isConnected(context)) {
                val crypto = cryptoService.getCrypto(currencyCode).execute().body()
                val roomCrypto = crypto?.map { mapResponseToDbModel(it) }
                cryptoDao.insertAll(roomCrypto ?: return arrayListOf())
            }

         cryptoDao.getCrypto()
        } catch (e :Exception) {
            arrayListOf()
        }
    }

    suspend fun getCryptoById(id: String): CryptoDetails? {
        return try {
            return cryptoDao.getCryptoById(id)
        } catch (e :Exception) {
            null
        }
//        return try {
//            if (NetworkUtil.isConnected(context)) {
//                val crypto = cryptoDao.getCrypto().execute().body()
//                val roomCrypto = crypto?.map { mapResponseToDbModel(it) }
//                cryptoDao.insertAll(roomCrypto ?: return null)
//            }
//
//            return cryptoDao.getCryptoById(id)
//        } catch (e: Exception) {
//            null
//        }
    }

    suspend fun updateCrypto(crypto: CryptoDetails) {
        cryptoDao.update(crypto)
    }



    private fun mapResponseToDbModel(response: CryptoResponseDetails): CryptoDetails {
        return CryptoDetails(
            id = response.id,
            symbol = response.symbol,
            name = response.name,
            image = response.image,
            current_price = response.current_price,
            market_cap = response.market_cap,
            high_24h = response.high_24h,
            price_change_percentage_24h = response.price_change_percentage_24h,
            market_cap_change_percentage_24h = response.market_cap_change_percentage_24h,
            low_24h = response.low_24h,
            favourite = false
        )
    }

}