package com.example.logerino.user

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateUserInfoScreen(
    navController: NavController,
    userViewModel: UserViewModel = viewModel()
) {

    val getData = userViewModel.state.value
    var adresse by rememberSaveable { mutableStateOf("") }
    var fornavn by rememberSaveable { mutableStateOf("") }
    var etternavn by rememberSaveable { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current


    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) {padding ->

        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)
        {
            Text(text = "Brukerinfo",
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp)
            Text(text = "Adresse: ${getData.adresse}",
                fontSize = 25.sp)
            Text(text = "Fornavn: ${getData.fornavn}",
                fontSize = 25.sp)
            Text(text = "Etternavn: ${getData.etternavn}",
                fontSize = 25.sp)

            //Sjekker at input field ikke er tomme
            val isInputFieldsEmpty = adresse.isNotBlank() &&
                    fornavn.isNotBlank() && etternavn.isNotBlank()


            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = adresse,
                onValueChange = { adresse = it },
                label = { Text(text = "Adresse") }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = fornavn,
                onValueChange = { fornavn = it },
                label = { Text(text = "Fornavn") }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = etternavn,
                onValueChange = { etternavn = it },
                label = { Text(text = "Etternavn") }
            )

            Button(onClick = { if (isInputFieldsEmpty)
            {
                userViewModel.updateUserInfoFromFireStore(adresse, fornavn, etternavn)
                Toast.makeText(context, "Informasjon er oppdatert", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(context, "Du må fylle ut alle feltene", Toast.LENGTH_SHORT).show()
            } }) {
                Text(text = "Oppdater info")

            }
        }
    }
}
