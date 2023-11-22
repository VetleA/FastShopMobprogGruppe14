package GUI.HjemSkjerm

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
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
import com.example.hioffastshopgruppe14.R

@Composable
fun HjemSkjerm(){
    Column(modifier = Modifier.fillMaxSize()) {
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
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Mauris volutpat egestas eros, in aliquet eros tincidunt in. " +
                    "Praesent est purus, fermentum in posuere in, accumsan a nunc. " +
                    "Aenean viverra fringilla ipsum, vitae ullamcorper felis aliquet molestie. " +
                    "Vivamus luctus, felis et placerat fermentum, justo ex molestie eros, " +
                    "id venenatis est elit quis lectus. Etiam tempor, quam vitae eleifend iaculis, " +
                    "orci massa lacinia eros, id ullamcorper nisl lacus at nulla. Etiam posuere " +
                    "nibh ac odio suscipit rhoncus. Quisque semper elementum diam, vel placerat " +
                    "mi accumsan sit amet. Nullam accumsan augue ultrices pulvinar congue. " +
                    "Fusce varius, velit sed luctus mollis, ex dolor feugiat massa, ut commodo " +
                    "nulla ligula nec justo. Phasellus fringilla nibh nec mattis vestibulum. " +
                    "Vivamus efficitur placerat gravida. Praesent eget purus consectetur, " +
                    "tincidunt elit non, vehicula nibh.",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp),
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic
        )





    }
}

@Preview(showBackground = true)
@Composable
fun HjemSkjermPreview(){
    HjemSkjerm()
}