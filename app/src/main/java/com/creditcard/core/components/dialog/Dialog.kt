package com.creditcard.core.components.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.creditcard.R

@Composable
fun Dialog(
    icon: ImageVector,
    title: String,
    text: String,
    onCanceled: () -> Unit,
    onConfirm: () -> Unit,
    isVisible: Boolean,
) {
    if(isVisible) {
        AlertDialog(
            icon = { Icon(icon, contentDescription = title) },
            title = { Text(text = title) },
            text = { Text(text = text) },
            onDismissRequest = { onCanceled() },
            confirmButton = {
                TextButton(
                    onClick = { onConfirm() }
                ) { Text(stringResource(id = R.string.action_confirm)) }
            },
            dismissButton = {
                TextButton(
                    onClick = { onCanceled() }
                ) { Text(stringResource(id = R.string.action_cancel)) }
            },
        )
    }
}