package com.creditcard.features.authenticator.signin.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.creditcard.NavScreens
import com.creditcard.R
import com.creditcard.core.components.button.ButtonFilled
import com.creditcard.core.components.button.ButtonOutline
import com.creditcard.core.components.button.ButtonSwitch
import com.creditcard.core.components.dialog.Dialog
import com.creditcard.core.components.input.TextField
import com.creditcard.features.authenticator.signin.di.SignInModule
import com.creditcard.core.theme.JetPackComposeCreditCardTheme
import com.creditcard.features.common.ui.UiState
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.GlobalContext

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = koinViewModel()
) {

    val navController = rememberNavController()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.padding(0.dp, 32.dp)){
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_lock),
                contentDescription = stringResource(id = R.string.label_email),
                modifier = Modifier.padding(0.dp, 2.dp, 4.dp, 0.dp)
            )
            Text(
                fontSize = 28.sp,
                text = stringResource(R.string.title_signin).uppercase(),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.Bottom),
            )
        }

        Text(
            fontSize = 20.sp,
            text = stringResource(R.string.title_wellcome),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            fontSize = 14.sp,
            text = stringResource(R.string.title_wellcome_description),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Start),
            lineHeight = 1.5.em
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            label = stringResource(id = R.string.label_email),
            getValueChange = { viewModel.setEmail(it) },
            value = viewModel.state.email.value,
            hasError = viewModel.state.email.hasError,
            messageError = viewModel.state.email.message,
            leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_email),
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            label = stringResource(id = R.string.label_password),
            placeholder = stringResource(id = R.string.placeholder_password),
            getValueChange = { viewModel.setPassword(it) },
            value = viewModel.state.password.value,
            hasError = viewModel.state.password.hasError,
            messageError =  viewModel.state.password.message,
            leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_password),
            isPassword = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        ButtonSwitch(
            text = stringResource(id = R.string.title_keep_connected),
            isChecked = viewModel.state.isKeepConnected,
            onCheckedChange = {
                viewModel.setKeepConnected(it)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ButtonFilled(
            text = stringResource(R.string.action_access).uppercase(),
            isEnabled = viewModel.isEnabledButton(),
            isLoading = viewModel.state.ui is UiState.Loading,
            onClick = { viewModel.setSubmit() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ButtonOutline(
            text = stringResource(R.string.title_forgot_password).uppercase(),
            onCLick = {}
        )
    }

    if(viewModel.state.ui is UiState.Success<String>) {
        navController.navigate(NavScreens.Home.route)
    }

    Dialog(
        icon = ImageVector.vectorResource(id = R.drawable.ic_password),
        title = stringResource(id = R.string.title_info),
        text = stringResource(id = R.string.msg_error_unknow),
        onConfirm = {},
        onCanceled = {},
        isVisible = (viewModel.state.ui is UiState.Error)
    )
}

@Composable
fun GreetingPreview() {
    JetPackComposeCreditCardTheme {
        GlobalContext.startKoin {
            modules(SignInModule.instance)
        }.also {
            SignInScreen()
        }
    }
}