package com.creditcard.core.components.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonFilled(
    containerColor: Color = MaterialTheme.colorScheme.primary,
    text: String,
    isEnabled: Boolean,
    isLoading: Boolean,
    onClick: () -> Unit,
    isWrap: Boolean = false,
) {
    Button(
        modifier = if(isWrap) Modifier.wrapContentWidth().height(55.dp) else Modifier.fillMaxWidth().height(55.dp),
        colors = ButtonColors(
            containerColor = containerColor,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        enabled = isEnabled,
        shape = RoundedCornerShape(10.dp),
        onClick = {
            onClick()
        },
        content = {
            if(isLoading) {
                CircularProgressIndicator()
            } else {
                Text(text = text, color = if(isEnabled) {
                    MaterialTheme.colorScheme.onPrimary } else { MaterialTheme.colorScheme.onSurfaceVariant })
            }
        }
    )
}
