package com.creditcard.ui.screens.authenticator.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.creditcard.R
import com.creditcard.ui.theme.Blue40
import com.creditcard.ui.theme.JetPackComposeCreditCardTheme

@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            fontSize = 26.sp,
            text = stringResource(R.string.title_signup).uppercase(),
            fontWeight = FontWeight.SemiBold
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetPackComposeCreditCardTheme {
        SignUpScreen()
    }
}