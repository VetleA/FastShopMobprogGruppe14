package GUI.Stores


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.StoreData

@Composable
fun StoreResultList(stores: List<StoreData>) {


    LazyColumn {
        items(stores) { store ->
            StoreResultItem(store)
        }
    }
}

@Composable
fun StoreResultItem(store: StoreData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)

    ) {
        Divider(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(5.dp)
        )

        Text(text = "Navn på butikk: ${store.name ?: "N/A"}")
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = "Adresse: ${store.address ?: "N/A"}")
    }
}
