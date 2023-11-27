package GUI.HjemSkjerm

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
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
fun HjemSkjerm(navController: NavController){
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.fast), contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.CenterHorizontally)
                .padding(20.dp)
                .clip(CircleShape)
                .border(
                    BorderStroke(width = 3.dp, Color.LightGray),
                    CircleShape
                )
        )

        Text(
            text = "Velkommen til FastShop!",
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

        Text(
            text = "Prisoversikt",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic
        )

        TextButton(onClick = { navController.navigate(Screens.ProductSearchScreen.route) }) {
            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null)
            Text(text = "Finn de beste prisene")
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }

        Text(
            text = "Finn dine nærmeste butikker!",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic
        )

        TextButton( onClick = { navController.navigate(Screens.FetchStoresScreen.route)}) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
            Text(text = "Mine nærbutikker")
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }
    }
}