package com.common.network.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class GetCurrentUserLocationUseCase(
    private val locationProviderClient: FusedLocationProviderClient,
    private val context: Context,
) {

    fun fetchUpdates() = callbackFlow {
        val locationRequest = LocationRequest.Builder(1000).build()
        val callback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)

                val location = locationResult.lastLocation
                if (location != null) {
                    val userLocation = MapLocation(
                        latitude = location.latitude,
                        longitude = location.longitude,
                    )
                    trySend(userLocation)
                } else {
                    trySend(null)
                }
            }
        }

        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationProviderClient.requestLocationUpdates(
                locationRequest,
                callback,
                Looper.getMainLooper()
            )
        } else {
            send(null)
            close()
        }

        awaitClose {
            locationProviderClient.removeLocationUpdates(callback)
        }
    }
}