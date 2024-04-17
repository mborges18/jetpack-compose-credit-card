package com.creditcard.ui.screens.authenticator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.creditcard.R
import com.creditcard.ui.screens.authenticator.signin.SignInScreen
import com.creditcard.ui.screens.authenticator.signup.SignUpScreen
import com.creditcard.ui.theme.JetPackComposeCreditCardTheme

@Composable
fun AuthenticatorScreen(
    navController: NavHostController
) {
    LazyColumn (modifier = Modifier.fillMaxSize()) {
        item {
            TabLavViewPager(
                items = listOf(
                    stringResource(R.string.title_signin).uppercase(),
                    stringResource(R.string.title_signup).uppercase()
                ),
                contentView = {
                    when (it) {
                        0 -> SignInScreen()
                        1 -> SignUpScreen()
                    }
                }
            )
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