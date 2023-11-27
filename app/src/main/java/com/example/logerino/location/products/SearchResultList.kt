package GUI.Produkter

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Data

@Composable
fun SearchResultList(searchResults: List<Data>) {
    LazyColumn {
        items(searchResults) { product ->
            SearchResultItem(product)
        }
    }
}

@Composable
fun SearchResultItem(product: Data) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Text(
            text = "Navn: ${product.name ?: "N/A"}",
            )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Nåværende pris: ${product.currentPrice ?: 0.0}",
            )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Butikk: ${product.store?.name ?: 0.0}",
        )
    }
}