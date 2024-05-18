package com.creditcard.core.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ButtonSwitch(
    text: String? = String(),
    isChecked: Boolean,
    onCheckedChange: (isChecked: Boolean) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth())
    {
        Switch(
            checked = isChecked,
            onCheckedChange = {
                onCheckedChange(it)
            },
           thumbContent = {
               Box(
                   modifier = Modifier
                       .fillMaxSize()
                       .aspectRatio(100f)
                       .background(color = MaterialTheme.colorScheme.onPrimary),
               )
           },
            colors = SwitchColors(
                checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                checkedTrackColor = if(isSystemInDarkTheme()) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.primary,
                checkedBorderColor = Color.Transparent,
                checkedIconColor = MaterialTheme.colorScheme.primary,

                uncheckedThumbColor = MaterialTheme.colorScheme.onPrimary,
                uncheckedTrackColor = if(isSystemInDarkTheme()) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surfaceContainer,
                uncheckedBorderColor = Color.Transparent,
                uncheckedIconColor = MaterialTheme.colorScheme.surfaceContainer,

                disabledCheckedThumbColor = MaterialTheme.colorScheme.onPrimary,
                disabledCheckedTrackColor = MaterialTheme.colorScheme.surfaceContainer,
                disabledCheckedBorderColor = Color.Transparent,
                disabledCheckedIconColor = MaterialTheme.colorScheme.surfaceContainer,

                disabledUncheckedThumbColor = MaterialTheme.colorScheme.onPrimary,
                disabledUncheckedTrackColor = MaterialTheme.colorScheme.surfaceContainer,
                disabledUncheckedBorderColor = Color.Transparent,
                disabledUncheckedIconColor = MaterialTheme.colorScheme.surfaceContainer
            )
        )
        text?.let { value ->
            if (value.isNotEmpty()) {
                Text(
                    text = value,
                    fontWeight = FontWeight.Bold,
                    color = if(isChecked) {
                        MaterialTheme.colorScheme.onBackground } else { MaterialTheme.colorScheme.onSurfaceVariant },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(8.dp, 0.dp)
                )
            }
        }
    }
}