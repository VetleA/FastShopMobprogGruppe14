package com.example.logerino.navigation

sealed class Screens(val route: String) {
    object SignInScreen : Screens(route = "SignIn_Screen")

    object SignUpScreen : Screens(route = "SignUp_Screen")

    object HandlelisteScreen : Screens(route = "Home_Screen")

    object DetailScreen : Screens(route = "Detail_Screen")

    object ListScreen : Screens(route = "List_Screen")

    object UserInfoScreen : Screens(route = "User_Info_Screen")

    object UpdateInfoScreen : Screens(route = "Update_User_Info_Screen")

    object SpesifikkHandlelisteScreen : Screens(route = "Spesifikk_Handleliste_Screen")

    object OpprettHandlelisteScreen : Screens(route = "Opprett_Handleliste_Screen")

    object HjemScreen : Screens(route = "Hjem_Screen")

    object ProfilScreen : Screens(route = "Profil_Screen")

    object LocationFetcherScreen : Screens(route = "Location_Screen")

    object FetchStoresScreen : Screens(route = "Fetch_Store_Screen")

    object InstillingerScreen : Screens(route = "Innstillinger_Screen")

    object AboutScreen : Screens(route = "About_Screen")

    object HelpScreen : Screens(route = "Help_Screen")

    object ProductSearchScreen : Screens(route = "Product_Search_Screen")


}