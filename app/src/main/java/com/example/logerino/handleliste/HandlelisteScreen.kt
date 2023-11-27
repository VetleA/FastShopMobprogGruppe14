package com.example.logerino.handleliste


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.logerino.navigation.Screens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HandlelisteScreen(
    navController: NavController,
    handlelisteViewModel: HandlelisteViewModel = viewModel(),
)

{
    var tittel by rememberSaveable { mutableStateOf("") }
    var vare by rememberSaveable { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current


    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            OutlinedTextField(
                modifier = Modifier

                    .fillMaxWidth()
                    .padding(16.dp),
                value = tittel,
                onValueChange = { tittel = it },
                label = { Text(text = "Tittel") }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp),
                value = vare,
                onValueChange = { vare = it },
                label = { Text(text = "Handleliste") }
            )

            Button(onClick = {handlelisteViewModel.addDataToFireStore(tittel, vare)
                navController.navigate(Screens.SpesifikkHandlelisteScreen.route)
            Toast.makeText(context, "Handleliste lagt til!", Toast.LENGTH_SHORT).show()}) {
                Text(text = "Lagre handleliste")
            }
            Divider(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(45.dp)
            )

            }

        }


    }
