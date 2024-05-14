package com.dyablonskyi.cryptocurrencyapp.data.repository

import com.dyablonskyi.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.dyablonskyi.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.dyablonskyi.cryptocurrencyapp.data.remote.dto.CoinDto
import com.dyablonskyi.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
): CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}