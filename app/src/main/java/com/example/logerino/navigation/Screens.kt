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


}