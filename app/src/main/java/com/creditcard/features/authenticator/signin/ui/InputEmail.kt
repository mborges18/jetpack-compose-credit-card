package com.creditcard.features.authenticator.signin.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import com.creditcard.R

@Composable
fun InputEmail(
    setEmail: (value: String) -> Unit,
    email: String,
    hasError: Boolean,
    messageError: String
) {
    var emailHasFocus by rememberSaveable { mutableStateOf(false) }

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
                tint = handleColorIconFocus(hasFocus = emailHasFocus, hasError = hasError)
            )
        },
        isError = hasError,
        supportingText = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = messageError,
                color = MaterialTheme.colorScheme.error
            )
        },
        value = email,
        onValueChange = { value ->
            setEmail(value)
        },
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
    )
}