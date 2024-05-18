package com.creditcard.domain.models.authenticator.signin

import com.creditcard.domain.models.states.SignInUseCaseState

data class SignInModel(
    var email: String = String(),
    var password: String = String(),
    var isKeepConnected: Boolean = false,
    var state: SignInUseCaseState = SignInUseCaseState.Initial,
)
