package com.common.network.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.Tasks

class LocationManager(
    private val locationProviderClient: FusedLocationProviderClient,
    private val context: Context,
) {

    fun getCurrentLocation(): MapLocation? {
        return if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            try {
                val result =
                    Tasks.await(
                        locationProviderClient.lastLocation
                    )

                MapLocation(
                    result.latitude,
                    result.longitude
                )
            } catch (e: Exception) {
                null
            }
        } else {
            null
        }
    }
}