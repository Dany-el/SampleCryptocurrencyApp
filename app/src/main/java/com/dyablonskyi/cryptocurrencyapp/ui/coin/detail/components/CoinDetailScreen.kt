package com.dyablonskyi.cryptocurrencyapp.ui.coin.detail.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dyablonskyi.cryptocurrencyapp.R
import com.dyablonskyi.cryptocurrencyapp.ui.coin.detail.CoinDetailState
import com.dyablonskyi.cryptocurrencyapp.ui.coin.detail.CoinDetailViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailScreen(
    state: CoinDetailState
) {
    Box(modifier = Modifier.fillMaxSize()) {
        state.coin?.let { coin ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                            style = MaterialTheme.typography.headlineMedium,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(8f)
                        )
                        Text(
                            text = if (coin.isActive) "active" else "inactive",
                            color = if (coin.isActive) colorResource(id = R.color.dark_green) else Color.Red,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(2f)
                        )
                    }
                    Spacer(modifier = Modifier.size(15.dp))
                    Text(
                        text = coin.description,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Spacer(modifier = Modifier.size(15.dp))
                    Text(
                        text = "Tags",
                        style = MaterialTheme.typography.headlineSmall,
                    )
                    Spacer(modifier = Modifier.size(15.dp))
                    FlowRow(
                        maxItemsInEachRow = 4,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        coin.tags.forEach { tag ->
                            TagItem(tagName = tag)
                        }
                    }
                    Spacer(modifier = Modifier.size(15.dp))
                    Text(
                        text = "Team members",
                        style = MaterialTheme.typography.headlineSmall,
                    )
                    Spacer(modifier = Modifier.size(15.dp))
                }
                items(coin.team) { member ->
                    TeamMemberItem(member = member)
                    Divider(modifier = Modifier.padding(vertical = 10.dp))
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