package com.rocqjones.me_logic.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices

object PermissionsUtils {

    fun hasLocationPermission(context: Context): Boolean {
        return try {
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        } catch (e: Exception) {
            false
        }
    }

    fun hasCameraPermission(context: Context): Boolean {
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
    fun getCurrentLocation(context: Context, callback: (Double, Double) -> Unit) {
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
}