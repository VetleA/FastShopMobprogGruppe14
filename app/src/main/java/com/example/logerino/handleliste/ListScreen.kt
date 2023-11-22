package com.example.logerino.handleliste


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.logerino.models.ListeHandle
import com.example.logerino.data.Datasource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    navController: NavController,
) {


    HandleListeCardScreen()


}

@Composable
fun HandleListeCardScreen() {
    HandleList(

        vareList = Datasource().loadHandlelister()
    )
}


@Composable
fun HandleList(vareList: List<ListeHandle>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        items(vareList) { vareList ->
            HandlelisteCard(
                listeHandle = vareList,
                modifier = Modifier.padding(8.dp))
        }
    }
}


@Composable
fun HandlelisteCard(listeHandle: ListeHandle, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Text(
                text = LocalContext.current.getString(listeHandle.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}