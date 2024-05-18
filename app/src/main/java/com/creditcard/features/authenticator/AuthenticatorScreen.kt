package com.creditcard.features.authenticator

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.creditcard.R
import com.creditcard.features.authenticator.signin.ui.SignInScreen
import com.creditcard.features.authenticator.signup.ui.SignUpScreen
import com.creditcard.core.theme.JetPackComposeCreditCardTheme
import com.creditcard.features.authenticator.signin.di.SignInModule
import org.koin.core.context.GlobalContext

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

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)

@Composable
fun GreetingPreview() {
    JetPackComposeCreditCardTheme {
        GlobalContext.startKoin {
            modules(SignInModule.instance)
        }.also {
            AuthenticatorScreen(rememberNavController())
        }
    }
}