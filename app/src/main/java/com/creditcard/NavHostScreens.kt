package com.creditcard

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.creditcard.features.authenticator.AuthenticatorScreen
import com.creditcard.features.home.HomeScreen
import com.creditcard.features.splash.ui.SplashScreen

@Composable
fun NavHostScreens(
    navController: NavHostController,
    startDestination: String = NavScreens.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = NavScreens.Splash.route) { SplashScreen(navController) }
        composable(route = NavScreens.Authenticator.route) { AuthenticatorScreen(navController) }
        composable(route = NavScreens.Home.route) { HomeScreen(navController) }
    }
}