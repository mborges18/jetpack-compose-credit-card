package com.creditcard.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.creditcard.ui.screens.authenticator.AuthenticatorScreen
import com.creditcard.ui.screens.home.HomeScreen
import com.creditcard.ui.screens.splash.SplashScreen

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