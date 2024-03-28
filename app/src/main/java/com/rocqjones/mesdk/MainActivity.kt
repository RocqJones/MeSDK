package com.rocqjones.mesdk

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rocqjones.me_design.screens.BottomSheetDialogScreen
import com.rocqjones.me_design.screens.EndlessScreen
import com.rocqjones.me_design.screens.HomeScreen
import com.rocqjones.me_design.ui.theme.MeSDKTheme
import com.rocqjones.me_logic.models.Screen

/**
 * This is the first launcher screen of the SDK.
 * You'll access all feature modules from this screen.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeSDKTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppMain()
                }
            }
        }
    }
}

@Composable
fun MyAppMain() {
    val navController = rememberNavController()

    NavHost(
        navController, startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController)
        }

        composable(Screen.EndlessScreen.route) {
            EndlessScreen(navController)
        }

        composable(Screen.BottomSheetDialogScreen.route) {
            BottomSheetDialogScreen(navController)
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 450,
    name = "Light"
)
@Preview(
    showBackground = true,
    widthDp = 450,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Composable
fun DefaultPreview() {
    MeSDKTheme {
        MyAppMain()
    }
}