package com.creditcard.core.components.button

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            }
        )
        text?.let { value ->
            if (value.isNotEmpty()) {
                Text(
                    text = value,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(8.dp, 0.dp)
                )
            }
        }
    }
}