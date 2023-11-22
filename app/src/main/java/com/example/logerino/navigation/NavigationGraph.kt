package com.example.logerino.navigation

import GUI.Navigasjon.BottomNavigationBar
import GUI.Navigasjon.NavigationHoved
import GUI.theme.BottomNavItem
import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.logerino.detail.DetailScreen
import com.example.logerino.handleliste.HandlelisteScreen
import com.example.logerino.login.SignInScreen
import com.example.logerino.handleliste.HandlelisteViewModel
import com.example.logerino.handleliste.ListScreen
import com.example.logerino.handleliste.EndreHandlelisteScreen
import com.example.logerino.handleliste.SpesifikkHandlelisteScreen
import com.example.logerino.user.UpdateUserInfoScreen
import com.example.logerino.user.UserInfoScreen
import com.example.logerino.sign_up.SignUpScreen

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
                    route = Screens.UserInfoScreen.route,
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
        NavigationGraph()
    }
}



@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SpesifikkHandlelisteScreen.route
    ) {
        composable(route = Screens.SignInScreen.route) {
            SignInScreen(navController)

        }
        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(navController)

        }
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
        composable(route = Screens.DetailScreen.route){
            DetailScreen(detailViewModel = null, handlelisteId = "") {

            }
        }

        composable(route = Screens.ListScreen.route){
            ListScreen(navController)

        }

        composable(route = Screens.OpprettHandlelisteScreen.route){
            EndreHandlelisteScreen(navController)

        }

        composable(route = Screens.SpesifikkHandlelisteScreen.route){
            SpesifikkHandlelisteScreen(navController)

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

/*
NavHost(
        navController = navController,
        startDestination = Screens.SignUpScreen.route
    ) {
        navigation(startDestination = Screens.SignUpScreen.route,
            route = "innlogging"){
            composable(route = Screens.SignUpScreen.route) {
                SignUpScreen(navController)
                val viewModel = it.sharedViewModel<SampleViewModel>(navController)

            }

            composable(route = Screens.SignInScreen.route) {
                SignInScreen()
                val viewModel = it.sharedViewModel<SampleViewModel>(navController)

            }
        }
        navigation(startDestination = Screens.HomeScreen.route,
            route = "hjem"){
            composable(route = Screens.HomeScreen.route) {
                Home(navController)
                val viewModel = it.sharedViewModel<SampleViewModel>(navController)

            }
        }
    }
 */

