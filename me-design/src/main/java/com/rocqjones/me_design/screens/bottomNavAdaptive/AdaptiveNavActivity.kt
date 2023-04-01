package com.rocqjones.me_design.screens.bottomNavAdaptive

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rocqjones.me_design.base.BaseActivity
import com.rocqjones.me_design.ui.theme.MeSDKTheme

class AdaptiveNavActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeSDKTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Check Android CI & Branch protection")
                }
            }
        }

        // Test custom snackBar
        toastUtils.showSnackBar("This screen successfully lunched")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Test $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MeSDKTheme {
        Greeting("Android")
    }
}