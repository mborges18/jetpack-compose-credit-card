package com.creditcard.core.components.input

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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.creditcard.R

@Composable
fun TextField(
    label: String,
    placeholder: String = String(),
    getValueChange: (value: String) -> Unit,
    value: String,
    maxValue: Int = 40,
    hasError: Boolean,
    messageError: String,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    isPassword: Boolean = false
) {
    var hasFocus by rememberSaveable { mutableStateOf(false) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                hasFocus = it.isFocused
            },
        label = { Text(text = label, color = handleColorFocus(hasFocus = hasFocus, hasError = hasError)) },
        placeholder = { Text(text = placeholder) },
        isError = hasError,
        supportingText = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = messageError,
                color = MaterialTheme.colorScheme.error
            )
        },
        value = value,
        onValueChange = {
        if(it.length < maxValue)
            getValueChange(it)
        },
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        visualTransformation = if(isPassword) { if (passwordVisible){ VisualTransformation.None } else { PasswordVisualTransformation() }} else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),
        leadingIcon = {
            leadingIcon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = label,
                    tint = handleColorFocus(hasFocus = hasFocus, hasError = hasError)
                )
            }
        },
        trailingIcon = {
            if(isPassword) {
                IconButton(onClick = {
                    passwordVisible = passwordVisible.not()
                }) {
                    Icon(
                        imageVector = if (passwordVisible)
                            ImageVector.vectorResource(id = R.drawable.ic_eye_open)
                        else ImageVector.vectorResource(id = R.drawable.ic_eye_close),
                        contentDescription = label,
                        tint = handleColorFocus(hasFocus = hasFocus, hasError = hasError)
                    )
                }
            } else {
                trailingIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = label,
                        tint = handleColorFocus(hasFocus = hasFocus, hasError = hasError)
                    )
                }
            }
        },
    )
}

@Composable
fun handleColorFocus(hasFocus: Boolean, hasError: Boolean) =
    if(hasError){
        MaterialTheme.colorScheme.error
    } else {
        if(hasFocus) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.onSurface
        }
    }

