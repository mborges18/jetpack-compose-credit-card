package com.creditcard.features.authenticator.signin.ui

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

        InputEmail(
            setEmail = { viewModel.setEmail(it) },
            email = viewModel.modelState.email,
            hasError = viewModel.getStateInvalid().emailIsInvalid,
            messageError = viewModel.getStateInvalid().emailMessage
        )

        Spacer(modifier = Modifier.height(8.dp))

        InputPassword(
            setPassword = { viewModel.setPassword(it) },
            password = viewModel.modelState.password,
            hasError = viewModel.getStateInvalid().passwordIsInvalid,
            messageError = viewModel.getStateInvalid().passwordMessage
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth())
        {
            Switch(
                checked = viewModel.modelState.isKeepConnected,
                onCheckedChange = {
                    viewModel.setKeepConnected(it)
                }
            )
            Text(
                text = stringResource(id = R.string.title_keep_connected),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp, 0.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                disabledContentColor = Color.DarkGray
            ),
            enabled = viewModel.isEnabledButton(),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                viewModel.setSubmit()
            },
            content = {
                if(viewModel.getStateIsLoading()) {
                    CircularProgressIndicator()
                } else {
                    Text(text = stringResource(R.string.action_access).uppercase())
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            onClick = {

            },
            content = {
                Text(text = stringResource(R.string.title_forgot_password).uppercase())
            }
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

@Preview(showBackground = true)
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