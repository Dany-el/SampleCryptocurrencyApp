package com.dyablonskyi.cryptocurrencyapp.ui.coin.detail.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dyablonskyi.cryptocurrencyapp.ui.theme.CryptocurrencyAppTheme

@Composable
fun TagItem(
    tagName: String
) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.extraLarge
            )
            .padding(10.dp)
    ){
        Text(
            text = tagName,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
private fun TagItemPreview() {
    CryptocurrencyAppTheme {
        TagItem(tagName = "Tag123")
    }
}