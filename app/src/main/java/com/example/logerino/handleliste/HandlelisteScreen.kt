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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HandlelisteScreen(
    navController: NavController,
    handlelisteViewModel: HandlelisteViewModel = viewModel(),
)

{
    var tittel by rememberSaveable { mutableStateOf("") }
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
            Toast.makeText(context, "Handleliste lagt til!", Toast.LENGTH_SHORT).show()}) {
                Text(text = "Lagre handleliste")
            }

            Button(onClick = { handlelisteViewModel.getDataFromFireStore() }) {
                Text(text = "Hent data")

            }


            Button(onClick = { handlelisteViewModel.getMultipleDocumentsFromFireStore() }) {
                Text(text = "Hent en mengde data")

            }

            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {


                    Text(
                        text = tittel,
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



/*
Button(onClick = {
                scope.launch {
                    handlelisteViewModel.addDataToFireStore(tittel, vare)
                    val snackbarResult = snackbarHostState
                        .showSnackbar(uiState.value,
                            actionLabel = "Cancel",
                            withDismissAction = true)
                    when (snackbarResult) {
                        SnackbarResult.ActionPerformed -> {
                            Log.d("SnackbarTest", "Utført")

                        }SnackbarResult.Dismissed -> {
                        Log.d("SnackbarTest", "Dissmissed")
                    }
                    }} }) {
                Text(text = "Legg til data") }}
 */





/*
    val homeUiState = homeViewModel?.homeUiState ?: HomeUiState()

    var openDialog by remember {
        mutableStateOf(false)
    }

    var selectedHandleliste:Handleliste? by remember {
        mutableStateOf(null)
    }


    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {navController.navigate(Screens.DetailScreen.route)}) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)

        }
    }, topBar = {
        TopAppBar(
            navigationIcon = {},
            actions = {
                      IconButton(onClick = { homeViewModel?.signOut()
                          navController.navigate(Screens.SignInScreen.route)}) {
                          Icon(imageVector = Icons.Default.ExitToApp, contentDescription = null)
                          
                      }

            }, title = { Text(text = "Hjemskjerm")})
    }


    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            when(homeUiState.handlelisteListe){
                is StorageRepository.Resources.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
                is StorageRepository.Resources.Success -> {

                    LazyVerticalGrid(columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(16.dp)){
                        items(homeUiState.handlelisteListe.data ?: emptyList()){ handleliste ->
                            HandlelisteItem(handleliste = handleliste, onLongClick = { openDialog = true
                            selectedHandleliste = handleliste}) {
                                onHandlelisteClick.invoke(handleliste.documentId)
                                
                            }

                        }
                    }
                }
                else -> {
                    Text(
                        text = homeUiState.handlelisteListe.throwable
                            ?.localizedMessage ?: "Ukjent feil")
                }

        }

    }

}
    LaunchedEffect(key1 = homeViewModel?.hasUser){
        if (homeViewModel?.hasUser == false){
            navController.navigate(Screens.SignInScreen.route)
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HandlelisteItem(handleliste: Handleliste,
                    onLongClick: () -> Unit,
                    onClick: () -> Unit){

    Card(modifier = Modifier
        .combinedClickable(
            onLongClick = { onLongClick.invoke() },
            onClick = { onClick.invoke() })
        .padding(8.dp)
        .fillMaxWidth()) {

        Column {
            Text(text = handleliste.title,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Clip,
                modifier = Modifier.padding(4.dp)
           )
            Spacer(modifier = Modifier.size(4.dp))

            Text(text = handleliste.description,
                style =  MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(4.dp),
                maxLines = 4)
            
            Spacer(modifier = Modifier.size(4.dp))

            Text(text = formateDate(handleliste.timestamp),
                style =  MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.End),
                maxLines = 4)

    }
    }
}

private fun formateDate(timestamp: Timestamp):String{
    val sdf = SimpleDateFormat("mm-dd-yy hh:mm", Locale.getDefault())
    return sdf.format(timestamp.toDate())

 */

