package com.example.weatherapp

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.tasks.await
import java.util.*

class LocationViewModel : ViewModel() {
    var currentCity by mutableStateOf("")
        private set

    var latitude by mutableDoubleStateOf(0.0)
        private set

    var longitude by mutableDoubleStateOf(0.0)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set

    suspend fun getCurrentLocation(context: Context) {
        try {
            isLoading = true
            errorMessage = ""

            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            val cancellationTokenSource = CancellationTokenSource()

            // Get current location with high accuracy
            val location = fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            ).await()

            if (location != null) {
                latitude = location.latitude
                longitude = location.longitude

                // Get city name from coordinates using Geocoder
                val geocoder = Geocoder(context, Locale.getDefault())
                val addresses: List<Address>? = geocoder.getFromLocation(
                    latitude, longitude, 1
                )

                if (!addresses.isNullOrEmpty()) {
                    val address = addresses[0]
                    currentCity = address.locality
                        ?: address.subAdminArea
                                ?: address.adminArea
                                ?: "Unknown Location"
                } else {
                    currentCity = "Unknown Location"
                    errorMessage = "Could not determine city name"
                }
            } else {
                errorMessage = "Could not get location"
            }
        } catch (e: SecurityException) {
            errorMessage = "Location permission denied"
        } catch (e: Exception) {
            errorMessage = "Error: ${e.message}"
        } finally {
            isLoading = false
        }
    }
}