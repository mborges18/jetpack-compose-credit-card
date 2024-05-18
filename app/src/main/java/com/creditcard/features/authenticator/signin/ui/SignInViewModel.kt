package com.creditcard.features.authenticator.signin.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creditcard.features.authenticator.signin.domain.model.SignInModel
import com.creditcard.features.authenticator.signin.domain.model.SignInState
import com.creditcard.features.authenticator.signin.domain.usecase.SignInUseCaseState
import com.creditcard.features.authenticator.signin.domain.usecase.SignInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInUseCase: SignInUseCase,
): ViewModel() {

    private val _uiState = MutableStateFlow<SignInUseCaseState>(SignInUseCaseState.Initial)
    val uiState: StateFlow<SignInUseCaseState> = _uiState.asStateFlow()

    var modelState by mutableStateOf(SignInModel())

    fun setEmail(value: String) {
        resetEmail()
        modelState = modelState.copy(email = value)
    }

    private fun resetEmail() {
        modelState.state = SignInUseCaseState.Invalid(
            SignInState(
            emailIsInvalid = false,
            emailMessage = String()
        )
        )
    }

    fun setPassword(value: String) {
        resetPassword()
        modelState = modelState.copy(password = value)
    }

    private fun resetPassword() {
        modelState.state = SignInUseCaseState.Invalid(
            SignInState(
            passwordIsInvalid = false,
            passwordMessage = String()
        )
        )
    }

    fun setKeepConnected(value: Boolean) {
        modelState = modelState.copy(isKeepConnected = value)
    }

    fun isEnabledButton() = (modelState.email.length > 6
            && modelState.password.length > 5 && modelState.password.length < 13) && getStateIsLoading().not()

    fun getStateInvalid(): SignInState {
        return if(modelState.state is SignInUseCaseState.Invalid<*>) {
            ((modelState.state as SignInUseCaseState.Invalid<*>).data as SignInState)
        } else {
            SignInState()
        }
    }

    fun getStateIsLoading(): Boolean {
        return if(modelState.state is SignInUseCaseState.Loading<*>) {
            ((modelState.state as SignInUseCaseState.Loading<*>).data as Boolean)
        } else {
            false
        }
    }

    fun setSubmit() = viewModelScope.launch {
        try {
            modelState = modelState.copy(state = SignInUseCaseState.Loading(true))
            modelState = modelState.copy(state = signInUseCase.execute(modelState))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}