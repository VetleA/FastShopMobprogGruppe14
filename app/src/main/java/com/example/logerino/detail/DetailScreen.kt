package com.example.logerino.detail

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.logerino.ui.theme.LogerinoTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel?,
    handlelisteId:String,
    onNavigate: () -> Unit,
) {
    val context = LocalContext.current

    val detailUiState = detailViewModel?.detailUiState ?: DetailUiState()

    val isFormsNotBlank = detailUiState.handleliste.isNotBlank() &&
            detailUiState.title.isNotBlank()

    val isHandlelisteIdNotBlank = handlelisteId.isNotBlank()
    val icon = if (isFormsNotBlank) Icons.Default.Refresh
    else Icons.Default.Check

    LaunchedEffect(key1 = Unit) {
        if (isHandlelisteIdNotBlank) {
            detailViewModel?.getHandleliste(handlelisteId)
        } else {
            detailViewModel?.resetState()
        }
    }

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            if (isHandlelisteIdNotBlank) {
                detailViewModel?.updateHandleliste(handlelisteId)
            } else {
                detailViewModel?.addHandleliste()
            }
        }) {
            Icon(imageVector = icon, contentDescription = null)

        }
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (detailUiState.handlelisteAddedStatus) {
                Toast.makeText(context, "Handlelisten ble lagt til!", Toast.LENGTH_LONG).show()
                detailViewModel?.resetHandlelisteAddedStatus()
                onNavigate.invoke()
            }
        }
    }
    if (detailUiState.updatehandlelisteStatus) {
        Toast.makeText(context, "Handlelisten ble oppdatert!", Toast.LENGTH_LONG).show()
        detailViewModel?.resetHandlelisteAddedStatus()
        onNavigate.invoke()

    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 30.dp, end = 30.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(
            text = "Handleliste",
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            color = Color.Gray,
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = detailUiState.title,
            onValueChange = {detailViewModel?.onTitleChange(it)},
            label = { Text(text = "Tittel")}
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = detailUiState.handleliste,
            onValueChange = { detailViewModel?.onHandlelisteChange(it) },
            label = { Text(text = "Varer") },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp)
        )

    }


}



