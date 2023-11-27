package com.example.logerino.navigation

import GUI.HjemSkjerm.HjemSkjerm
import GUI.Innstillinger.AboutScreen
import GUI.Innstillinger.HelpScreen
import GUI.Innstillinger.InnstillingerScreen
import GUI.Navigasjon.BottomNavigationBar
import GUI.Produkter.ProductSearchScreen
import GUI.Profil.ProfilScreen
import GUI.Stores.FetchStoresScreen
import GUI.theme.BottomNavItem
import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.logerino.handleliste.HandlelisteScreen
import com.example.logerino.login.SignInScreen
import com.example.logerino.handleliste.HandlelisteViewModel
import com.example.logerino.handleliste.EndreHandlelisteScreen
import com.example.logerino.handleliste.SpesifikkHandlelisteScreen
import com.example.logerino.user.UpdateUserInfoScreen
import com.example.logerino.user.UserInfoScreen
import com.example.logerino.sign_up.SignUpScreen
import service.ApiService
import service.module.ApiServiceModule

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigasjonMaster(){
    val navController = rememberNavController()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    //Gir oss muligheten til å ikke vise navbar på login og opprett bruker siden
    when (navBackStackEntry?.destination?.route) {
        Screens.SignInScreen.route -> {
            bottomBarState.value = false
        }
        Screens.SignUpScreen.route -> {
            bottomBarState.value = false
        }
        Screens.HjemScreen.route -> {
            bottomBarState.value = true
        }
    }


    Scaffold(
        bottomBar = {
            BottomNavigationBar( items = listOf(
                BottomNavItem(
                    name = "Hjem",
                    route = Screens.HjemScreen.route,
                    icon = Icons.Default.Home
                ),
                BottomNavItem(
                    name = "Profil",
                    route = Screens.ProfilScreen.route,
                    icon = Icons.Default.Person
                ),
                BottomNavItem(
                    name = "Handleliste",
                    route = Screens.SpesifikkHandlelisteScreen.route,
                    icon = Icons.Default.List
                ),
                BottomNavItem(
                    name = "Produkter",
                    route = Screens.ProductSearchScreen.route,
                    icon = Icons.Default.ShoppingCart
                ),
                BottomNavItem(
                    name = "Butikk",
                    route = Screens.FetchStoresScreen.route,
                    icon = Icons.Default.Search
                ),
                BottomNavItem(
                    name = "Instillinger",
                    route = Screens.InstillingerScreen.route,
                    icon = Icons.Default.Settings
                ),
            ),
                navController =navController,
                bottomBarState = bottomBarState,
                onItemClick ={
                    navController.navigate(it.route)
                }
            )
        }
    ){
        NavigationGraph(navController = navController)
    }
}



@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    val apiService: ApiService by lazy {
        ApiServiceModule.createApiService()
    }

    NavHost(
        navController = navController,
        startDestination = Graph.AUTH
    ) {
        navigation(route = Graph.AUTH, startDestination = Screens.SignInScreen.route){
            composable(route = Screens.SignInScreen.route) {
                SignInScreen(navController)
            }
            composable(route = Screens.SignUpScreen.route) {
                SignUpScreen(navController)
            }
        }
        navigation(route = Graph.HOME, startDestination = Screens.HjemScreen.route){
            composable(route = Screens.UpdateInfoScreen.route) {
                UpdateUserInfoScreen(navController)

            }
            composable(route = Screens.UserInfoScreen.route) {
                UserInfoScreen(navController)

            }
            composable(route = Screens.HandlelisteScreen.route) {
                HandlelisteScreen(
                    navController,
                    handlelisteViewModel = HandlelisteViewModel()
                )
            }
            composable(route = Screens.OpprettHandlelisteScreen.route){
                EndreHandlelisteScreen(navController)

            }

            composable(route = Screens.SpesifikkHandlelisteScreen.route){
                SpesifikkHandlelisteScreen(navController)

            }

            composable(route = Screens.HjemScreen.route){
                HjemSkjerm(navController)

            }

            composable(route = Screens.ProfilScreen.route){
                ProfilScreen(navController)

            }
            composable(route = Screens.FetchStoresScreen.route){
                FetchStoresScreen(apiService = apiService)

            }
            composable(route = Screens.InstillingerScreen.route){
                InnstillingerScreen(navController)

            }

            composable(route = Screens.AboutScreen.route){
                AboutScreen(navController)

            }

            composable(route = Screens.HelpScreen.route){
                HelpScreen(navController)

            }

            composable(route = Screens.ProductSearchScreen.route){
                ProductSearchScreen(apiService = apiService)

            }
        }
    }
}


// <T> Gir oss her muligheten til å sende inn en viewmodel som ikke er spesifisert
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController) : T {
    val navnGraphRoute = destination.parent?.route ?: return  hiltViewModel()
    val parentEntry = remember(this){
        navController.getBackStackEntry(navnGraphRoute)
    }
    return  hiltViewModel(parentEntry)
}