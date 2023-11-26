package com.example.logerino.handleliste

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpesifikkHandlelisteScreen(
    navController: NavController,
    handlelisteViewModel: HandlelisteViewModel = viewModel(),
)

{ var tittel by rememberSaveable { mutableStateOf("") }
    var vare by rememberSaveable { mutableStateOf("") }
    val getData = handlelisteViewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val uiState = handlelisteViewModel.uiState
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

            Button(onClick = {handlelisteViewModel.getDocumentFromFireStore(tittel)
                Toast.makeText(context, "Handleliste lagt til!", Toast.LENGTH_SHORT).show()}) {
                Text(text = "Søk etter handleliste")
            }


            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {


                Text(
                    text = getData.title.toString(),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    modifier = Modifier.padding(4.dp)
                )
                Spacer(modifier = Modifier.size(4.dp))

                Text(
                    text = getData.varer.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(4.dp),
                    maxLines = 4
                )

                Spacer(modifier = Modifier.size(4.dp))

            }


        }

    }


}
