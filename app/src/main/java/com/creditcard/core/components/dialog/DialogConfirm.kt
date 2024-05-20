package com.creditcard.core.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.Info
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.creditcard.R
import com.creditcard.core.components.button.ButtonFilled
import com.creditcard.core.theme.colorInfo

@Composable
fun DialogConfirm(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    text: String,
    onCanceled: () -> Unit,
    onConfirm: () -> Unit,
    isVisible: Boolean,
) {
    if(isVisible) {
        Dialog(
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
            ),
            onDismissRequest = {},
            content = {
                Column(
                    modifier = modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = MaterialTheme.colorScheme.surfaceContainerHigh),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                        .fillMaxWidth()
                        .background(color = colorInfo)
                        .padding(vertical = 16.dp)) {
                        Icon(imageVector = icon, contentDescription = title, modifier.size(32.dp),
                            tint = Color.White)
                    }
                    Column(
                        modifier = modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(text = title, fontSize = 22.sp)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = text)
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Box (
                            modifier = modifier.weight(1.0f, true),
                        ) {
                            ButtonFilled(
                                containerColor = colorInfo,
                                text = stringResource(R.string.action_confirm),
                                isEnabled = true,
                                isLoading = false,
                                onClick = { onConfirm() },
                            )

                        }
                        Box (
                            modifier = modifier.weight(1.0f, true),
                        ) {
                            ButtonFilled(
                                containerColor = MaterialTheme.colorScheme.onPrimary,
                                text = stringResource(R.string.action_cancel),
                                isEnabled = true,
                                isLoading = false,
                                onClick = { onCanceled() }
                            )
                        }


                    }
                }
            }
        )
    }
}

@Composable
fun DialogError(
    icon: ImageVector,
    title: String,
    text: String,
    onClick: () -> Unit,
    isVisible: Boolean,
) {
    if(isVisible) {
        AlertDialog(
            icon = { Icon(imageVector = icon, contentDescription = title) },
            title = { Text(text = title) },
            text = { Text(text = text) },
            onDismissRequest = {},
            confirmButton = {
                ButtonFilled(
                    text = stringResource(R.string.action_ok),
                    isEnabled = true,
                    isLoading = false,
                    onClick = { onClick() }
                )
            }
        )
    }
}