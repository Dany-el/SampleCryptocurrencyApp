package com.dyablonskyi.cryptocurrencyapp.domain.repository

import com.dyablonskyi.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.dyablonskyi.cryptocurrencyapp.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}