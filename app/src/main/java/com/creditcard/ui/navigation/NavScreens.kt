package com.creditcard.ui.navigation

sealed class NavScreens(val route: String) {
    object Splash : NavScreens("splash")
    object Authenticator : NavScreens("authenticator")
    object Home : NavScreens("home")
}