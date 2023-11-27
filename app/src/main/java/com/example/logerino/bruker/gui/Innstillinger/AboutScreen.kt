package GUI.Innstillinger


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.logerino.R
import com.example.logerino.navigation.Screens

@Composable
fun AboutScreen(navController: NavController){
    Column (modifier = Modifier.fillMaxSize()){
        TextButton(onClick = { navController.navigate(Screens.InstillingerScreen.route) }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }

        Text(
            text = "Om applikasjonen",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            fontStyle = FontStyle.Italic
        )

        Divider(
            thickness = 1.dp,
            color = Color.Black,
            modifier = Modifier.padding(10.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.folk), contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .size(180.dp)
                .align(Alignment.CenterHorizontally)
                .padding(20.dp)
                .clip(CircleShape)
                .border(
                    BorderStroke(width = 3.dp, Color.LightGray),
                    CircleShape
                )
        )

        Text(
            text = "Skapt av folk for folk. Gjør hverdagen enklere",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp),
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic
        )

        Image(
            painter = painterResource(id = R.drawable.kot), contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .size(180.dp)
                .align(Alignment.CenterHorizontally)
                .padding(20.dp)
                .clip(CircleShape)
                .border(
                    BorderStroke(width = 3.dp, Color.LightGray),
                    CircleShape
                )
        )

        Text(
            text = "Jetpack Compose forenkler UI-utvikling som sørger for at " +
                    "brukere får en smidig opplevelse",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp),
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic
        )
        Image(
            painter = painterResource(id = R.drawable.hiof), contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .size(180.dp)
                .align(Alignment.CenterHorizontally)
                .padding(20.dp)
                .clip(CircleShape)
                .border(
                    BorderStroke(width = 3.dp, Color.LightGray),
                    CircleShape
                )
        )
        Text(
            text = "Utviklet ved Høgskolen i Østfold",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp),
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic
        )

        Divider(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(45.dp)
        )
    }
}
