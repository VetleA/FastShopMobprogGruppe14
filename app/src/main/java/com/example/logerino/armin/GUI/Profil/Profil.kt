package GUI.Profil

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logerino.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profil(){

    val notification = rememberSaveable { mutableStateOf("") }
    if (notification.value.isNotEmpty()){
        Toast.makeText(LocalContext.current, notification.value, Toast.LENGTH_LONG).show()
        notification.value = ""
    }
    var name by rememberSaveable{ mutableStateOf("default name") }
    var epost by rememberSaveable{ mutableStateOf("default email") }
    var bio by rememberSaveable{ mutableStateOf("default bio") }




    Column (modifier = Modifier.fillMaxSize()){
        Text(
            text = "Min profil",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic
        )
        Divider(
            thickness = 1.dp,
            color = Color.Black,
            modifier = Modifier.padding(10.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.profil), contentScale = ContentScale.Crop,
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

        Button(onClick = {}, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Endre bilde")
        }


        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Name", modifier = Modifier.width(100.dp))
            TextField(value = name, onValueChange = {name = it},
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black
                ))
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Epost", modifier = Modifier.width(100.dp))
            TextField(value = epost, onValueChange = {epost = it},
                colors = TextFieldDefaults.textFieldColors(

                    textColor = Color.Black
                ))
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            verticalAlignment = Alignment.Top
        ){
            Text(text = "Bio", modifier = Modifier.width(100.dp).padding(top = 8.dp))
            TextField(value = bio, onValueChange = {bio = it},
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                ),
                singleLine = false,
                modifier = Modifier.height(150.dp)
            )
        }
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(8.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){ // I clickable går funksjonalitet som f. eks tilbake til en annen side
                Text(text = "Cancel", modifier = Modifier.clickable { notification.value = "Avbryt" })
                Text(text = "Save", modifier = Modifier.clickable { notification.value = "Profil oppdatert" })
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ProfilPreview(){
    Profil()
}