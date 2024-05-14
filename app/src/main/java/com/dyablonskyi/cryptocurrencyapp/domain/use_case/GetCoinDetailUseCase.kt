package com.dyablonskyi.cryptocurrencyapp.domain.use_case

import com.dyablonskyi.cryptocurrencyapp.common.Resource
import com.dyablonskyi.cryptocurrencyapp.data.remote.dto.toCoinDetail
import com.dyablonskyi.cryptocurrencyapp.domain.model.CoinDetail
import com.dyablonskyi.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}