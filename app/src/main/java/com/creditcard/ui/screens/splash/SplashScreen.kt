package com.creditcard.ui.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.creditcard.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.creditcard.ui.navigation.NavScreens
import com.creditcard.ui.theme.JetPackComposeCreditCardTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold (containerColor = MaterialTheme.colorScheme.primary) {
        it.calculateBottomPadding()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                fontSize = 26.sp,
                text = stringResource(R.string.title_compose).uppercase(),
                color = colorResource(id = R.color.white),
                fontWeight = FontWeight.SemiBold
            )
            Text(
                fontSize = 22.sp,
                text = stringResource(R.string.title_credit_card).lowercase(),
                color = colorResource(id = R.color.white),
                letterSpacing =  TextUnit(14.toFloat(), TextUnitType.Sp)
            )
        }

        rememberCoroutineScope().launch {
            call(viewModel = viewModel)
            uiState.let { isUserLogged ->
                when(isUserLogged) {
                    true -> navController.navigate(route = NavScreens.Home.route)
                    false -> navController.navigate(route = NavScreens.Authenticator.route)
                }
            }
        }
    }
}

suspend fun call(viewModel: SplashViewModel) {
    withContext(Dispatchers.Default) {
        viewModel.isUserLogged()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetPackComposeCreditCardTheme {
        SplashScreen(rememberNavController())
    }
}