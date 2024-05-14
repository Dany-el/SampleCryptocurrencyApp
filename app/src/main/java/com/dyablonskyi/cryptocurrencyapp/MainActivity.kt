package com.dyablonskyi.cryptocurrencyapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dyablonskyi.cryptocurrencyapp.ui.Screen
import com.dyablonskyi.cryptocurrencyapp.ui.coin.detail.CoinDetailState
import com.dyablonskyi.cryptocurrencyapp.ui.coin.detail.CoinDetailViewModel
import com.dyablonskyi.cryptocurrencyapp.ui.coin.detail.components.CoinDetailScreen
import com.dyablonskyi.cryptocurrencyapp.ui.coin.list.CoinListViewModel
import com.dyablonskyi.cryptocurrencyapp.ui.coin.list.components.CoinListScreen
import com.dyablonskyi.cryptocurrencyapp.ui.theme.CryptocurrencyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrencyAppTheme {
                CoinApp()
            }
        }
    }
}

@Composable
fun CoinApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        tonalElevation = 10.dp,
        color = MaterialTheme.colorScheme.background
    ) {
        val navController = rememberNavController()

        CoinNavController(navController)
    }
}


@Composable
fun CoinNavController(
    navController: NavHostController,
    coinListViewModel: CoinListViewModel = viewModel(),
    coinDetailViewModel: CoinDetailViewModel = viewModel()
) {
    val listState by coinListViewModel.state
    val coinDetailState by coinDetailViewModel.state

    NavHost(
        navController = navController,
        startDestination = Screen.CoinListScreen.route
    ) {
        composable(Screen.CoinListScreen.route) {
            CoinListScreen(
                onItemClick = { coin ->
                    navController.navigateToCoinDetail(coin.id)
                },
                state = listState
            )
        }
        composable(
            route = Screen.CoinDetailScreen.routeWithArgs,
            arguments = Screen.CoinDetailScreen.arguments
        ) {navBackStackEntry ->
            // To prevent unnecessary looping recomposition after GETing details of a coin
            LaunchedEffect(Unit) {
                val coinId = navBackStackEntry.arguments?.getString(Screen.CoinDetailScreen.COIN_DETAIL_ARG) ?: ""
                coinDetailViewModel.getCoin(coinId)
            }
            CoinDetailScreen(coinDetailState)
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
//        restoreState = true
        launchSingleTop = true
    }

fun NavHostController.navigateToCoinDetail(coinId: String) =
    this.navigateSingleTopTo("${Screen.CoinDetailScreen.route}/$coinId")
