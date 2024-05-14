package com.dyablonskyi.cryptocurrencyapp.ui.coin.list

import com.dyablonskyi.cryptocurrencyapp.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val errorMsg: String = ""
)
