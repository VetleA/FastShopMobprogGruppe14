package GUI.Stores



import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import model.StoreData
import service.ApiService


@Composable
fun FetchStoresScreen(apiService: ApiService) {
    val coroutineScope = rememberCoroutineScope()
    var stores by remember { mutableStateOf<List<StoreData>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Button(
            onClick = {
                coroutineScope.launch {
                    try {
                        val response = apiService.getPhysicalStores(
                            59.12887F,
                            11.352395F,
                            "Bearer Y6Z6BUFOvFReAKkZijrKkhk9f8MKZMhUAunvOLHQ"
                        )
                        println("=====>> Funker før IF" + response.isSuccessful)
                        if (response.isSuccessful) {
                            val result = response.body()
                            if (result != null) {
                                stores = result.data
                                errorMessage = null
                            } else {
                                errorMessage = "Ingen butikker innenfor en radius på 5km"
                            }
                        } else {
                            errorMessage = "Feil: ${response.code()}"
                        }
                    } catch (e: Exception) {
                        errorMessage = "Unntak fanget: ${e.message}"
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Finn butikker i nærheten")
        }
    }
    Spacer(modifier = Modifier.height(16.dp))

    if (errorMessage != null) {
        Text(errorMessage!!, modifier = Modifier.padding(16.dp))
    }

    if (stores.isNotEmpty()) {
        StoreResultList(stores)
    }
}
