package com.dyablonskyi.cryptocurrencyapp.ui.coin.detail

import com.dyablonskyi.cryptocurrencyapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val errorMsg: String = ""
)
