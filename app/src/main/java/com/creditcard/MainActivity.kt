package com.creditcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.creditcard.ui.navigation.NavHostScreens
import com.creditcard.ui.navigation.NavScreens
import com.creditcard.ui.screens.authenticator.AuthenticatorScreen
import com.creditcard.ui.theme.JetPackComposeCreditCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeCreditCardTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                ) {it.calculateBottomPadding()
                    val navController = rememberNavController()
                    NavHostScreens(navController, NavScreens.Authenticator.route)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetPackComposeCreditCardTheme {
        AuthenticatorScreen(rememberNavController())
    }
}