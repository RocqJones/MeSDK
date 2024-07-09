package com.rocqjones.me_design.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rocqjones.me_design.ui.theme.MeSDKTheme
import com.rocqjones.me_logic.models.CardInfo
import com.rocqjones.me_logic.providers.SampleDataSource

@Composable
fun GenHomeScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Good Afternoon,", style = MaterialTheme.typography.titleSmall)
        Text("Jones", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // Use LazyRow for efficient scrolling if the number of buttons can grow
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Use spacedBy for consistent spacing
        ) {
            items(listOf("Home", "Emergency", "GariSearch", "PataPesa", "More")) { item ->
                Button(onClick = {
                    // Handle navigation based on the item clicked
                    when (item) {
                        "Home" -> { /* navigate to home */ }
                        "Emergency" -> { /* navigate to emergency */ }
                        // ... and so on
                    }
                }) {
                    Text(item)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        val cardInfoList = getCardInfo()
        // Use LazyColumn for efficient list rendering
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(cardInfoList) { cardInfo ->
                Card(
                    modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) { // Add padding within the card
                        Text(cardInfo.title, style = MaterialTheme.typography.titleMedium)
                        Text(cardInfo.description, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}


// Dummy function to get card info (replace with actual data fetching)
fun getCardInfo(): List<CardInfo> {
    return SampleDataSource.genList
}

@Preview(
    showBackground = true,
    widthDp = 300,
    heightDp = 600
)
@Composable
fun GreetingPreview() {
    MeSDKTheme {
        GenHomeScreen()
    }
}
