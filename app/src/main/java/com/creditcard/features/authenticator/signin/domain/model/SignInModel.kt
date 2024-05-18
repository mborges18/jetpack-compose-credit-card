package com.creditcard.features.authenticator.signin.domain.model

import com.creditcard.features.authenticator.signin.domain.usecase.SignInUseCaseState

data class SignInModel(
    var email: String = String(),
    var password: String = String(),
    var isKeepConnected: Boolean = false,
    var state: SignInUseCaseState = SignInUseCaseState.Initial,
)
