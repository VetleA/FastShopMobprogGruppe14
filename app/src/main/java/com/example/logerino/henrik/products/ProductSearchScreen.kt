package GUI.Produkter


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import model.Data
import service.ApiService


@Composable
fun ProductSearchScreen(apiService: ApiService) {
    val coroutineScope = rememberCoroutineScope()
    var searchQuery by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf<List<Data>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        BasicTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.padding(16.dp)
        )

        Button(
            onClick = { //
                if (searchQuery.isNotEmpty()) {
                    coroutineScope.launch {
                        try {
                            val response = apiService.searchProducts(searchQuery, "Bearer PGWrnby4M2VEvveaCiAJCHecS2hrm4d8LnbAHylo")
                            println("=========>>>" + response.isSuccessful)
                            if (response.isSuccessful) {
                                val result = response.body()
                                if (result != null) {
                                    searchResults = result.data // Hent dataene fra resultatet
                                    errorMessage = null
                                } else {
                                    errorMessage = "Ingen resultater funnet"
                                }
                            } else {
                                errorMessage = "Feil ved henting av data: ${response.message()}"
                            }

                        } catch (e: Exception) {
                            errorMessage = "Feil ved nettverkskallet: ${e.message}"
                        }
                    }
                }
                else {
                    println("Søkefelte er tomt")
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Søk etter vare")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage != null) {
            Text(errorMessage!!, modifier = Modifier.padding(16.dp))
        }

        if (searchResults.isNotEmpty()) {
            SearchResultList(searchResults)
        }
    }
}