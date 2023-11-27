package GUI.Produkter


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import model.Data
import service.ApiService


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductSearchScreen(apiService: ApiService) {
    val coroutineScope = rememberCoroutineScope()
    var searchQuery by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf<List<Data>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    //Dropdown meny for antall varer i spørring
    var numberOfVarerSend by remember { mutableStateOf("5") }
    val numberOfVarer = arrayOf("1", "5", "10", "20", "25")
    var selectedVareNumber by remember { mutableStateOf(numberOfVarer[0]) }
    var expanded by remember { mutableStateOf(false) }

    //Dropdown meny for pris lav/høy i spørring
    var prisOfVarerSend by remember { mutableStateOf("price_desc") }
    val prisOfVarer = arrayOf("price_asc", "price_desc")
    var selectedPrisNumber by remember { mutableStateOf(numberOfVarer[0]) }
    var expanded2 by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text(text = "Søk") }
        )


        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Antall varer du ønsker i søk")

        ExposedDropdownMenuBox(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedVareNumber,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                numberOfVarer.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedVareNumber = item
                            numberOfVarerSend = item
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Pris")

        ExposedDropdownMenuBox(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            expanded = expanded2,
            onExpandedChange = {
                expanded2 = !expanded2
            }
        ) {
            TextField(
                value = selectedPrisNumber,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded2) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded2,
                onDismissRequest = { expanded2 = false }
            ) {
                prisOfVarer.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedPrisNumber = item
                            prisOfVarerSend = item
                            expanded2 = false
                        }
                    )
                }
            }
        }




        Button(
            onClick = { //
                if (searchQuery.isNotEmpty()) {
                    coroutineScope.launch {
                        try {
                            val response = apiService.searchProducts(
                                searchQuery,
                                searchNumber = numberOfVarerSend,
                                searchSort = prisOfVarerSend,
                                "Bearer PGWrnby4M2VEvveaCiAJCHecS2hrm4d8LnbAHylo")
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
