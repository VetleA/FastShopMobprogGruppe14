package GUI.Stores

import GUI.UserLocation.requestNewLocationData
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
    var currentLatitude by remember { mutableStateOf<Float?>(null) }
    var currentLongitude by remember { mutableStateOf<Float?>(null) }
    var isButtonEnabled by remember { mutableStateOf(true) }


    //Dropdown meny for antall butikker i søk
    var numberOfStoresSend by remember { mutableStateOf("1") }
    val numberOfStores = arrayOf("1", "5", "10", "30")
    var selectedNumberOfStores by remember { mutableStateOf(numberOfStores[0]) }
    var expanded1 by remember { mutableStateOf(false) }

    //Dropdown meny for antall km til butikk i søk
    var numberOfRadiusSend by remember { mutableStateOf("5") }
    val numberOfKmRadiusStores = arrayOf("1", "5", "10", "20", "25", "30", "50", "100")
    var selectedRadius by remember { mutableStateOf(numberOfKmRadiusStores[0]) }
    var expanded2 by remember { mutableStateOf(false) }



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

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Antall butikker i respons")

        ExposedDropdownMenuBox(
            expanded = expanded1,
            onExpandedChange = {
                expanded1 = !expanded1
            }
        ) {
            TextField(
                value = selectedNumberOfStores,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded1) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded1,
                onDismissRequest = { expanded1 = false }
            ) {
                numberOfStores.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedNumberOfStores = item
                            numberOfStoresSend = item
                            expanded1 = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Finn butikk innenfor angitt avstand (km)")

        ExposedDropdownMenuBox(
            expanded = expanded2,
            onExpandedChange = {
                expanded2 = !expanded2
            }
        ) {
            TextField(
                value = selectedRadius,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded2) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded2,
                onDismissRequest = { expanded2 = false }
            ) {
                numberOfKmRadiusStores.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedRadius = item
                            numberOfRadiusSend = item
                            expanded2 = false
                        }
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(8.dp))

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
                                        km = numberOfRadiusSend,
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
    Spacer(modifier = Modifier.height(16.dp))
    }


