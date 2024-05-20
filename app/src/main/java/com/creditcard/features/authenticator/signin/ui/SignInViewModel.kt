package com.creditcard.features.authenticator.signin.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creditcard.features.authenticator.signin.domain.model.SignInModel
import com.creditcard.features.authenticator.signin.domain.usecase.SignInUseCase
import com.creditcard.features.common.domain.usecase.ValidateEmailUseCase
import com.creditcard.features.common.domain.usecase.ValidatePasswordUseCase
import com.creditcard.features.common.ui.UiState
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInUseCase: SignInUseCase,
    private val emailUseCase: ValidateEmailUseCase,
    private val passwordUseCase: ValidatePasswordUseCase
): ViewModel() {

    var state by mutableStateOf(SignInState())

    fun setEmail(value: String) {
        state = state.copy(email = EmailState(value = value))
    }

    fun setPassword(value: String) {
        state = state.copy(password = PasswordState(value = value))
    }

    fun setKeepConnected(value: Boolean) {
        state = state.copy(isKeepConnected = value)
    }

    fun isEnabledButton() =
        (state.email.value.length > 6
                && state.password.value.length > 5
                && state.password.value.length < 13
                && (state.ui is UiState.Loading).not())

    fun setSubmit() = viewModelScope.launch {
        val resultEmail = emailUseCase.execute(state.email.value)
        val resultPassword = passwordUseCase.execute(state.password.value)

        state = state.copy(email = EmailState(
            value = state.email.value,
            message = resultEmail.message,
            hasError = resultEmail.hasError)
        )
        state = state.copy(password = PasswordState(
            value = state.password.value,
            message = resultPassword.message,
            hasError = resultPassword.hasError)
        )

        if (resultEmail.hasError.not() && resultPassword.hasError.not()) {
            try {
                state = state.copy(ui = UiState.Loading)
                val response = signInUseCase.execute(
                    SignInModel(state.email.value, state.password.value, state.isKeepConnected)
                )
                state = state.copy(ui = response)
            } catch (e: Exception) {
                e.printStackTrace()
                state = state.copy(ui = UiState.Initial)
            }
        }
    }
}