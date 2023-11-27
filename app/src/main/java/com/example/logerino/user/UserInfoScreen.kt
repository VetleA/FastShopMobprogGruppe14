package com.example.logerino.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.logerino.navigation.Screens

@Composable
fun UserInfoScreen(
    navController: NavController,
    dbViewModel: UserViewModel = viewModel()
) {
    val getData = dbViewModel.state.value

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Adresse: ${getData.adresse}")
            Text(text = "Fornavn: ${getData.fornavn}")
            Text(text = "Etternavn: ${getData.etternavn}")

            Button(onClick = { navController.navigate(Screens.UpdateInfoScreen.route) }) {
                Text(text = "Oppdater info")

            }
        }
    }
}
