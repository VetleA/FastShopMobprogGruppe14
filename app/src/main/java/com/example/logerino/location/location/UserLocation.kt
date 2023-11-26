package GUI.UserLocation

import android.Manifest
import android.content.Context
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager
import android.os.Looper
import com.google.android.gms.location.*

/* ======== Brukt youtube videoen under som mal for alt innenfor lokasjon henting!
   ======== KILDE: https://www.youtube.com/watch?v=ari3iD-3q8c&ab_channel=ChiragKachhadiya
   ======== chatGPT er brukt mye for å feilsøke problemer,
   ======== og det er blitt gjort mye endring med hjelp fra chatGPT
   ======== For å få lokasjonen til å stemme og ikke default lokasjon må man inn på emulator
   ======== instillinger å endre lokasjon manuelt der*/


fun requestNewLocationData(
    context: Context,
    fusedLocationClient: FusedLocationProviderClient,
    onLocationReceived: (Location?) -> Unit
) {
    if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        val locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 1000000
            fastestInterval = 500000
        }

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                onLocationReceived(locationResult.lastLocation)
            }
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    } else {
        onLocationReceived(null)
    }
}


