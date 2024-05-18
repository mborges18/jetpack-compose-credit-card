package com.creditcard.features.authenticator.signin.ui

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.creditcard.R
import com.creditcard.core.components.button.ButtonFilled
import com.creditcard.core.components.button.ButtonOutline
import com.creditcard.core.components.button.ButtonSwitch
import com.creditcard.core.components.input.TextField
import com.creditcard.features.authenticator.signin.di.SignInModule
import com.creditcard.core.theme.JetPackComposeCreditCardTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.GlobalContext

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

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
            value = viewModel.modelState.email,
            hasError = viewModel.getStateInvalid().emailIsInvalid,
            messageError = viewModel.getStateInvalid().emailMessage,
            leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_email),
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            label = stringResource(id = R.string.label_password),
            placeholder = stringResource(id = R.string.placeholder_password),
            getValueChange = { viewModel.setPassword(it) },
            value = viewModel.modelState.password,
            hasError = viewModel.getStateInvalid().passwordIsInvalid,
            messageError = viewModel.getStateInvalid().passwordMessage,
            leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_password),
            isPassword = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        ButtonSwitch(
            text = stringResource(id = R.string.title_keep_connected),
            isChecked = viewModel.modelState.isKeepConnected,
            onCheckedChange = {
                viewModel.setKeepConnected(it)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ButtonFilled(
            text = stringResource(R.string.action_access).uppercase(),
            isEnabled = viewModel.isEnabledButton(),
            isLoading = viewModel.getStateIsLoading(),
            onClick = { viewModel.setSubmit() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ButtonOutline(
            text = stringResource(R.string.title_forgot_password).uppercase(),
            onCLick = {}
        )
    }
}

@Composable
fun handleColorIconFocus(hasFocus: Boolean, hasError: Boolean) =
    if(hasError){
        MaterialTheme.colorScheme.error
    } else {
        if(hasFocus) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        }
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