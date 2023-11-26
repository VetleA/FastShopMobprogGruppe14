package GUI.Stores

import GUI.UserLocation.requestNewLocationData
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import model.StoreData
import service.ApiService


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FetchStoresScreen(apiService: ApiService) {
    val coroutineScope = rememberCoroutineScope()
    var stores by remember { mutableStateOf<List<StoreData>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    var locationText by remember { mutableStateOf("Trykk på knappen for å hente lokasjon") }
    var numberOfStoresSend by remember { mutableStateOf("") }
    var currentLatitude by remember { mutableStateOf<Float?>(null) }
    var currentLongitude by remember { mutableStateOf<Float?>(null) }
    var isButtonEnabled by remember { mutableStateOf(true) }
    var expanded by remember { mutableStateOf(false) }
    val numberOfStores = arrayOf("1", "5", "10")
    var selectedText by remember { mutableStateOf(numberOfStores[0]) }



   /*UserLocationComponent(context) { location ->
        locationText = location?.let {
            currentLatitude = it.latitude.toFloat()
            currentLongitude = it.longitude.toFloat()
            "Lokasjon: Lat ${it.latitude}, Long ${it.longitude}"
        } ?: "Tillatelse til lokasjon ikke gitt eller lokasjon ikke tilgjengelig"
    }*/


    Column(modifier = Modifier.padding(16.dp)) {
        Button(
            onClick = {
                isButtonEnabled = true
                requestNewLocationData(
                    context,
                    LocationServices.getFusedLocationProviderClient(context)
                ) {
                    locationText = it?.let { loc ->
                        currentLatitude = it.latitude.toFloat()
                        currentLongitude = it.longitude.toFloat()
                        "Lokasjon: Lat ${loc.latitude}, Long ${loc.longitude}"
                    } ?: "Kan ikke hente lokasjon"
                    println(locationText)
                }
            },
            enabled = isButtonEnabled
        ) {
            Text("Hent Lokasjon")
        }
        Text(text = locationText, modifier = Modifier.padding(top = 16.dp))

        Text(text = "Antall butikker i respons")

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                numberOfStores.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            numberOfStoresSend = item
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        currentLatitude?.let { latitude ->
                            currentLongitude?.let { longitude ->
                                try {
                                    val response = apiService.getPhysicalStores(
                                        latitude,
                                        longitude,
                                        size = numberOfStoresSend,
                                        "Bearer Y6Z6BUFOvFReAKkZijrKkhk9f8MKZMhUAunvOLHQ"
                                    )
                                    if (response.isSuccessful) {
                                        val result = response.body()
                                        if (result != null) {
                                            stores = result.data
                                            errorMessage = null
                                        } else {
                                            errorMessage =
                                                "Ingen butikker innenfor en radius på 5km"
                                        }
                                    } else {
                                        errorMessage = "Feil: ${response.code()}"
                                    }
                                } catch (e: Exception) {
                                    errorMessage = "${e.message}"
                                }
                            } ?: run {
                                errorMessage = "Lengdegrad ikke tilgjengelig"
                            }
                        } ?: run {
                            errorMessage = "Breddegrad ikke tilgjengelig"
                        }

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Finn butikker i nærheten")
            }
            Spacer(modifier = Modifier.height(16.dp))

            if (errorMessage != null) {
                Text(errorMessage!!, modifier = Modifier.padding(16.dp))
            }

            if (stores.isNotEmpty()) {
                StoreResultList(stores)
            }
        }
    }


