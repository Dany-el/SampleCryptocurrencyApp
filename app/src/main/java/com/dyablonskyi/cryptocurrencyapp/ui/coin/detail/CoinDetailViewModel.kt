package com.dyablonskyi.cryptocurrencyapp.ui.coin.detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dyablonskyi.cryptocurrencyapp.common.Constants
import com.dyablonskyi.cryptocurrencyapp.common.Resource
import com.dyablonskyi.cryptocurrencyapp.domain.use_case.GetCoinDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailUseCase: GetCoinDetailUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> get() = _state

    fun getCoin(coinId: String) {
        getCoinDetailUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(coin = result.data)
                    Log.i(
                        "Result",
                        "Success {${result.data}}"
                    )
                }

                is Resource.Error -> {
                    _state.value = CoinDetailState(
                        errorMsg = result.message ?: "An error occurred"
                    )
                    Log.i(
                        "Result",
                        "Error"
                    )
                }

                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                    Log.i(
                        "Result",
                        "Loading"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}