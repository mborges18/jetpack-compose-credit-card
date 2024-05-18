package com.creditcard.ui.screens.authenticator.signin

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.creditcard.R

@Composable
fun InputPassword(
    setPassword: (value: String) -> Unit,
    password: String,
    hasError: Boolean,
    messageError: String
) {
    var passwordHasFocus by rememberSaveable { mutableStateOf(false) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
    modifier = Modifier
    .fillMaxWidth()
    .onFocusChanged {
        passwordHasFocus = it.isFocused
    },
    label = { Text(text = stringResource(id = R.string.label_password)) },
    isError = hasError,
    supportingText = {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = messageError,
            color = MaterialTheme.colorScheme.error
        )
    },
    value = password,
    onValueChange = { value ->
        setPassword(value)
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
            tint = handleColorIconFocus(hasFocus = passwordHasFocus, hasError = hasError)
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
                tint = handleColorIconFocus(hasFocus = passwordHasFocus, hasError = hasError)
            )
        }
    }
    )
}