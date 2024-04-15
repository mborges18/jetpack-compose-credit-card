package com.creditcard.ui.screens.splash

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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import com.creditcard.R
import com.creditcard.ui.theme.Blue40

@Composable
fun SplashScreen() {
    Scaffold (containerColor = Blue40) {
        it.calculateBottomPadding()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                fontSize = 26.sp,
                text = stringResource(R.string.title_compose).uppercase(),
                color = colorResource(id = R.color.white),
                fontWeight = FontWeight.SemiBold
            )
            Text(
                fontSize = 22.sp,
                text = stringResource(R.string.title_credit_card).lowercase(),
                color = colorResource(id = R.color.white),
                letterSpacing =  TextUnit(14.toFloat(), TextUnitType.Sp)
            )
        }
    }
}