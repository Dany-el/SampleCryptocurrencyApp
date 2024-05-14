package com.dyablonskyi.cryptocurrencyapp.ui

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    data object CoinListScreen : Screen("coin_list")

    data object CoinDetailScreen : Screen("coin_detail") {
        const val COIN_DETAIL_ARG = "coinId"
        val routeWithArgs = "$route/{${COIN_DETAIL_ARG}}"
        val arguments = listOf(
            navArgument(COIN_DETAIL_ARG) { type = NavType.StringType }
        )
    }
}