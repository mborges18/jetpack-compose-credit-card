package com.creditcard.features.authenticator.signin.ui

import com.creditcard.features.authenticator.signin.domain.model.SignInModel
import com.creditcard.features.common.domain.model.EmailState
import com.creditcard.features.common.domain.model.PasswordState
import com.creditcard.features.common.ui.UiState
import com.creditcard.features.common.domain.model.ValidateResult

data class SignInState(
    val email: EmailState = EmailState(),
    val password: PasswordState = PasswordState(),
    val isKeepConnected: Boolean = false,
    val ui: UiState<String> = UiState.Initial
)
