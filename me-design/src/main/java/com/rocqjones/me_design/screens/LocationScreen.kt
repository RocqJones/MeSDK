package com.rocqjones.me_design.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
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
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices

@Composable
fun LocationScreen() {
    val currentContext = LocalContext.current
    var location by remember { mutableStateOf("Your location") }
    var cameraGranted by remember {
        mutableStateOf(hasCameraPermission(currentContext))
    }

    // Create a permission launcher
    val requestPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted: Boolean ->
                if (isGranted) {
                    // Permission granted, update the location
                    getCurrentLocation(currentContext) { lat, long ->
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
                    hasLocationPermission(currentContext) -> {
                        // Permission already granted, update the location
                        getCurrentLocation(currentContext) { lat, long ->
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

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = location)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                when {
                    hasCameraPermission(currentContext) -> {
                        cameraGranted = hasCameraPermission(currentContext)
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

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Camera Allowed: $cameraGranted")
    }
}

private fun hasLocationPermission(context: Context): Boolean {
    return try {
        ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    } catch (e: Exception) {
        false
    }
}

private fun hasCameraPermission(context: Context): Boolean {
    return try {
        ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    } catch (e: Exception) {
        false
    }
}

@SuppressLint("MissingPermission")
private fun getCurrentLocation(context: Context, callback: (Double, Double) -> Unit) {
    try {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val lat = location.latitude
                val long = location.longitude
                callback(lat, long)
            }
        }.addOnFailureListener { exception ->
            // Handle location retrieval failure
            exception.printStackTrace()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}