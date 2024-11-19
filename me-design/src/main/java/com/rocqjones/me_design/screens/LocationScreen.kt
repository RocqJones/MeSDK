package com.rocqjones.me_design.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rocqjones.me_design.ui.helpers.MediumVerticalSpacing
import com.rocqjones.me_logic.utils.PermissionsUtils

@Composable
fun LocationScreen() {
    val currentContext = LocalContext.current
    var location by remember { mutableStateOf("Your location") }
    var cameraGranted by remember {
        mutableStateOf(PermissionsUtils.hasCameraPermission(currentContext))
    }

    // Create a permission launcher
    val requestPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted: Boolean ->
                if (isGranted) {
                    // Permission granted, update the location
                    PermissionsUtils.getCurrentLocation(currentContext) { lat, long ->
                        location = "Latitude: $lat, Longitude: $long"
                    }
                }
            }
        )

    val requestCameraPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted: Boolean ->
                if (isGranted) {
                    // Permission granted, update the Text
                    cameraGranted = true
                }
            }
        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                when {
                    PermissionsUtils.hasLocationPermission(currentContext) -> {
                        // Permission already granted, update the location
                        PermissionsUtils.getCurrentLocation(currentContext) { lat, long ->
                            location = "Latitude: $lat, Longitude: $long"
                        }
                    }

                    else -> {
                        // Request location permission
                        requestPermissionLauncher.launch(
                            android.Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    }
                }
            }
        ) {
            Text(text = "Allow Location")
        }

        MediumVerticalSpacing()

        Text(text = location)

        MediumVerticalSpacing()

        Button(
            onClick = {
                when {
                    PermissionsUtils.hasCameraPermission(currentContext) -> {
                        cameraGranted = PermissionsUtils.hasCameraPermission(currentContext)
                    }

                    else -> {
                        // Request camera permission
                        requestCameraPermissionLauncher.launch(
                            android.Manifest.permission.CAMERA
                        )
                    }
                }
            }
        ) {
            Text(text = "Allow Camera")
        }

        MediumVerticalSpacing()

        Text(text = "Camera Allowed: $cameraGranted")
    }
}