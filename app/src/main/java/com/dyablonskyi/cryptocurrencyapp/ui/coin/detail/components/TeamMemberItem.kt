package com.dyablonskyi.cryptocurrencyapp.ui.coin.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dyablonskyi.cryptocurrencyapp.data.remote.dto.TeamMember
import com.dyablonskyi.cryptocurrencyapp.ui.theme.CryptocurrencyAppTheme

@Composable
fun TeamMemberItem(
    member: TeamMember,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = member.name,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = member.position,
            style = MaterialTheme.typography.bodyMedium,
            fontStyle = FontStyle.Italic
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TeamMemberItemPreview() {
    CryptocurrencyAppTheme {
        TeamMemberItem(
            member = TeamMember(
                id = "Some id",
                name = "First Second",
                position = "CEO"
            )
        )
    }
}