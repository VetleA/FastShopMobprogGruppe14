package GUI.Innstillinger

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.logerino.navigation.Screens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

@Composable
fun InnstillingerScreen(navController: NavController) {
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


        TextButton(onClick = { navController.navigate(Screens.ProfilScreen.route) }) {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
            Text(text = "Brukerkonto")
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }


        Divider(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
        )
        TextButton(onClick = { navController.navigate(Screens.ProfilScreen.route) }) {
            Icon(imageVector = Icons.Default.Settings, contentDescription = null)
            Text(text = "Utseende")
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }
        Divider(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
        )
        
        TextButton(onClick = { navController.navigate(Screens.HelpScreen.route) }) {
            Icon(imageVector = Icons.Default.Call, contentDescription = null)
            Text(text = "Hjelp og støtte")
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }
        Divider(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
        )
        TextButton(onClick = { navController.navigate(Screens.AboutScreen.route) }) {
            Icon(imageVector = Icons.Default.Info, contentDescription = null)
            Text(text = "Om applikasjonen")
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }
        Divider(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
        )
        TextButton(onClick = {
            navController.navigate(Screens.SignInScreen.route)
            FirebaseAuth.getInstance().signOut()}) {
            Icon(imageVector = Icons.Default.Clear, contentDescription = null)
            Text(text = "Logg ut")
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }
    }
}
