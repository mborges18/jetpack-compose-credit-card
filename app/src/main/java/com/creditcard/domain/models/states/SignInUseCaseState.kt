package com.creditcard.domain.models.states

sealed class SignInUseCaseState {
    data class Loading<Boolean>(val data: Boolean): SignInUseCaseState()
    data class Success<out T>(val data: T): SignInUseCaseState()
    object Initial: SignInUseCaseState()
    object Error: SignInUseCaseState()
    data class Invalid<out SignInState>(val data: SignInState): SignInUseCaseState()
}
