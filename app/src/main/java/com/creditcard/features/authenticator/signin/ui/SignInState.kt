package com.creditcard.features.authenticator.signin.ui

import com.creditcard.features.authenticator.signin.domain.model.SignInModel
import com.creditcard.features.common.ui.UiState
import com.creditcard.features.common.domain.model.ValidateResult

data class SignInState(
    val email: EmailState = EmailState(),
    val password: PasswordState = PasswordState(),
    val isKeepConnected: Boolean = false,
    val ui: UiState<String> = UiState.Initial
)

data class EmailState(
    val value: String = String(),
    val message: String = String(),
    val hasError: Boolean = false,
)

data class PasswordState(
    val value: String = String(),
    val message: String = String(),
    val hasError: Boolean = false,
)
