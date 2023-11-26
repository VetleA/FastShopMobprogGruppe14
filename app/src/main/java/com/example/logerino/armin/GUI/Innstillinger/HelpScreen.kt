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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.logerino.R
import com.example.logerino.navigation.Screens


@Composable
fun HelpScreen(navController: NavController){
    Column (modifier = Modifier.fillMaxSize()) {
        TextButton(onClick = { navController.navigate(Screens.InstillingerScreen.route) }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }

        Text(
            text = "Hjelp og støtte",
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
            painter = painterResource(id = R.drawable.hjelp), contentScale = ContentScale.Crop,
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
            text = "Telefon: (+47) 406 18 838",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp),
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic
        )

        Text(
            text = "Epost: support@fastshop.no",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp),
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic
        )

        Text(
            text = "Åpningstider mandag-fredag - fra kl.08:00 - 16:00",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp),
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic
        )



    }
}
