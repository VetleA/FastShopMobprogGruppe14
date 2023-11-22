package GUI.Navigasjon

import GUI.HjemSkjerm.HjemSkjerm
import GUI.Innstillinger.Innstillinger

import GUI.Profil.Profil
import GUI.theme.BottomNavItem
import GUI.theme.HIOFFastShopGruppe14Theme
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigasjon(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(items = listOf(
                BottomNavItem(
                    name = "Hjem",
                    route = "homehoved",
                    icon = Icons.Default.Home
                ),
                BottomNavItem(
                    name = "Profil",
                    route = "profilhoved",
                    icon = Icons.Default.Person
                ),
                BottomNavItem(
                    name = "Handleliste",
                    route = "handlelistehoved",
                    icon = Icons.Default.List
                ),
                BottomNavItem(
                    name = "Instillinger",
                    route = "instillingerhoved",
                    icon = Icons.Default.Settings
                ),
            ),
                navController =navController ,
                onItemClick ={
                    navController.navigate(it.route)
                }
            )
        }
    ){
        NavigationHoved(navController = navController)
    }
}

@Composable
fun NavigationHoved(navController: NavHostController){
    NavHost(navController = navController, startDestination = "homehoved"){
        composable("homehoved"){
            HjemSkjerm()
        }
        composable("handlelistehoved"){
            Handlelistehoved()
        }
        composable("instillingerhoved"){
            Innstillinger()
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(
        modifier = modifier,
        containerColor = Color.Blue,
        tonalElevation = 5.dp
    ){
        items.forEach{ item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Blue,
                    unselectedIconColor = Color.Gray),
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if(item.badgeCount > 0 ) {
                            BadgedBox(badge = { Badge{ Text( item.badgeCount.toString()) } }
                            ){
                                Icon(imageVector = item.icon,
                                    contentDescription =item.name
                                )
                            }

                        }
                        else{
                            Icon(imageVector = item.icon,
                                contentDescription =item.name
                            )
                        }
                        if (selected){
                            Text(text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )

                        }
                    }
                }
            )
        }
    }
}

@Composable
fun Handlelistehoved(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Liste")
    }
}








@Preview(showBackground = true)
@Composable
fun HIOFFastShopGruppe14ThemePreview(){
    HjemSkjerm()
}




/////////Brukt denne Youtube videoen under som mal for navigasjonsbaren
/* ======== KILDE: https://www.youtube.com/watch?v=4xyRnIntwTo ====== /
   ======== og det er blitt gjort en del endring fra orginal youtube video */