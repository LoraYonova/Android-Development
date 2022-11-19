package com.example.myapplication.repository

import android.content.Context
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

    suspend fun getCrypto(): List<CryptoDetails> {
        return try {
            if (NetworkUtil.isConnected(context)) {
                val crypto = cryptoService.getCrypto().execute().body()
                val roomCrypto = crypto?.map { mapResponseToDbModel(it) }
                cryptoDao.insertAll(roomCrypto ?: return arrayListOf())
            }

         cryptoDao.getCrypto()
        } catch (e :Exception) {
            arrayListOf()
        }
    }

    suspend fun getCryptoByName(name: String): CryptoDetails? {
        return try {
            if (NetworkUtil.isConnected(context)) {
                val crypto = cryptoService.getCryptoByName(name).execute().body()
                val roomCrypto = crypto?.map { mapResponseToDbModel(it) }
                cryptoDao.insertAll(roomCrypto ?: return null)
            }

            return cryptoDao.getCryptoByName(name)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun updateCrypto(crypto: CryptoDetails) {
        cryptoDao.update(crypto)
    }



    private fun mapResponseToDbModel(response: CryptoResponseDetails): CryptoDetails {
        return CryptoDetails(
            symbol = response.symbol,
            name = response.name,
            image = response.image,
            current_price = response.current_price,
            market_cap = (response.market_cap ?: 0) as Float,
            high_24h = (response.high_24h ?: 0) as Float,
            price_change_percentage_24h = (response.price_change_percentage_24h ?: 0) as Float,
            market_cap_change_percentage_24h = (response.market_cap_change_percentage_24h ?: 0) as Float,
            low_24h = (response.low_24h ?: 0) as Float,
            favourite = false
        )
    }

}