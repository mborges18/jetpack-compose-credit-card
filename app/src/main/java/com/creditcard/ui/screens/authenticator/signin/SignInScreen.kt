package com.creditcard.ui.screens.authenticator.signin

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.creditcard.R
import com.creditcard.di.AppModule
import com.creditcard.domain.models.authenticator.signin.SignInModel
import com.creditcard.ui.theme.JetPackComposeCreditCardTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext
import org.koin.mp.KoinPlatformTools

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = koinViewModel()
) {
    var emailHasFocus by rememberSaveable { mutableStateOf(false) }
    var passwordHasFocus by rememberSaveable { mutableStateOf(false) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val uiState by viewModel.uiState.collectAsState()
    var model by remember { mutableStateOf(SignInModel()) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isKeepConnected by remember { mutableStateOf(false) }

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

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    emailHasFocus = it.isFocused
                },
            label = { Text(text = stringResource(id = R.string.label_email)) },
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_email),
                    contentDescription = stringResource(id = R.string.label_email),
                    tint = emailHasFocus.handleColorIconFocus()
                )
            },
            isError = viewModel.model.isEmailValid(),
            supportingText = {
                if (viewModel.model.isEmailValid()) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.error_invalid_field),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            value = email,
            onValueChange = { value ->
                if (value.length < 55) {
                    email = value
                    viewModel.model.email = value
                    model = viewModel.model
                }
                            },
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),

        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    passwordHasFocus = it.isFocused
                },
            label = { Text(text = stringResource(id = R.string.label_password)) },
            value = password,
            onValueChange = { value ->
                if (value.length < 55) {
                    password = value
                    viewModel.model.password = value
                    model = viewModel.model
                }
            },
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            visualTransformation = if (passwordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_password),
                    contentDescription = stringResource(id = R.string.label_password),
                    tint = passwordHasFocus.handleColorIconFocus()
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisible = passwordVisible.not()
                }) {
                    Icon(
                        imageVector = if (passwordVisible)
                            ImageVector.vectorResource(id = R.drawable.ic_eye_open)
                        else ImageVector.vectorResource(id = R.drawable.ic_eye_close),
                        contentDescription = stringResource(id = R.string.label_password),
                        tint = passwordHasFocus.handleColorIconFocus()
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth())
        {
            Switch(
                checked = isKeepConnected,
                onCheckedChange = {
                    isKeepConnected = it
                    viewModel.model.isKeepConnected = it
                    model = viewModel.model
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
            enabled = model.isDataValid(),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                viewModel.signIn()
            },
            content = {
                Text(text = stringResource(R.string.action_access).uppercase())
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
fun Boolean.handleColorIconFocus() = if(this) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetPackComposeCreditCardTheme {
        GlobalContext.startKoin {
            modules(AppModule.instance)
        }.also {
            SignInScreen()
        }
    }
}