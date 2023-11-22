package GUI.UserLocation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.location.LocationServices

@Composable
fun LocationFetcherScreen() {
    val context = LocalContext.current
    var locationText by remember { mutableStateOf("Trykk på knappen for å hente lokasjon") }
    var isButtonEnabled by remember { mutableStateOf(true) }

    UserLocationComponent(context) { location ->
        locationText = location?.let {
            "Lokasjon: Lat ${it.latitude}, Long ${it.longitude}"
        } ?: "Tillatelse til lokasjon ikke gitt eller lokasjon ikke tilgjengelig"
        isButtonEnabled = true
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Button(
            onClick = {
                println("======>>>>")
                isButtonEnabled = true
                getLastLocation(context, LocationServices.getFusedLocationProviderClient(context)) {
                    locationText = it?.let { loc ->
                        "Lokasjon: Lat ${loc.latitude}, Long ${loc.longitude}"
                    } ?: "Kan ikke hente lokasjon"
                }
            },
            enabled = isButtonEnabled
        ) {
            Text("Hent Lokasjon")
        }
        Text(text = locationText, modifier = Modifier.padding(top = 16.dp))
    }
}
