package com.rocqjones.me_design.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rocqjones.me_logic.models.Screen

@Composable
fun HomeScreen(navController: NavHostController) {

    Box(modifier = Modifier.fillMaxSize()) {
        /**
         * When the button is clicked, should navigate to the respective UI
         */
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val padding = 8.dp
            val space = 16.dp

            Spacer(modifier = Modifier.padding(space))

            Text(
                "Welcome to the Me SDK!",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.secondary,
            )

            Spacer(modifier = Modifier.padding(space))

            // list
            Button(
                modifier = Modifier.padding(padding).fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                onClick = { navController.navigate(Screen.EndlessScreen.route) }
            ) {
                Text(
                    "Vertical Bouncing Scrolling List",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Bottom Sheets
            Button(
                modifier = Modifier.padding(padding).fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                onClick = { navController.navigate(Screen.BottomSheetDialogScreen.route) }
            ) {
                Text(
                    "Bottom Sheets Dialog",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}