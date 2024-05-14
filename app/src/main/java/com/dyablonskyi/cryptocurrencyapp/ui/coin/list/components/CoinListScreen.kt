package com.dyablonskyi.cryptocurrencyapp.ui.coin.list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dyablonskyi.cryptocurrencyapp.domain.model.Coin
import com.dyablonskyi.cryptocurrencyapp.ui.coin.list.CoinListState

@Composable
fun CoinListScreen(
    onItemClick: (Coin) -> Unit,
    state: CoinListState = CoinListState()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            Modifier.fillMaxSize()
        ) {
            items(
                items = state.coins,
                key = { coin -> coin.id }
            ) { coin: Coin ->
                CoinListItem(coin = coin) {
                    onItemClick(coin)
                }
            }
        }
        if (state.errorMsg.isNotBlank()) {
            Text(
                text = state.errorMsg,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}