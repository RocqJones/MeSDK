package com.rocqjones.mesdk

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rocqjones.me_design.screens.ListActivity
import com.rocqjones.me_design.ui.theme.MeSDKTheme

/**
 * This is the first launcher screen of the SDK.
 * You'll access all feature modules from this screen.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeSDKTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FirstScreen(
                        onMoveToListClicked = { navigateToList() }
                    )
                }
            }
        }
    }

    private fun navigateToList() {
        try {
            val i = Intent(this, ListActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

@Composable
fun FirstScreen(
    onMoveToListClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    /**
     * When the button is clicked, should navigate to the respective UI
     */
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Welcome to the Me SDK!",
            style = MaterialTheme.typography.headlineMedium,
            color =MaterialTheme.colorScheme.secondary
        )
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onMoveToListClicked // sets the sate to true
        ) {
            Text("Endless List")
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Composable
fun DefaultPreview() {
    MeSDKTheme {
        FirstScreen(onMoveToListClicked = {}) // empty lambda expression means "Do nothing" on click
    }
}