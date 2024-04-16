package com.creditcard.ui.screens.authenticator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.creditcard.R
import com.creditcard.ui.screens.authenticator.signin.SignInScreen
import com.creditcard.ui.screens.authenticator.signup.SignUpScreen
import com.creditcard.ui.theme.JetPackComposeCreditCardTheme

@Composable
fun AuthenticatorScreen() {
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) { paddingValues ->
        paddingValues.calculateBottomPadding()
        Column {
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
        AuthenticatorScreen()
    }
}