package GUI.Innstillinger

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Innstillinger() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            text = "Innstillinger",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Divider(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
        )

        TextButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
            Text(text = "Brukerkonto")
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }


        Divider(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
        )
        TextButton(onClick = { }) {
            Icon(imageVector = Icons.Default.Settings, contentDescription = null)
            Text(text = "Utseende")
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }
        Divider(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
        )
        
        TextButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Call, contentDescription = null)
            Text(text = "Hjelp og støtte")
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }
        Divider(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
        )
        TextButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Info, contentDescription = null)
            Text(text = "Om applikasjonen")
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InnstillingerPreview(){
    Innstillinger()
}