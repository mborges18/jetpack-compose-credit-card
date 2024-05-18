package com.creditcard.features.authenticator.signin.domain.model

data class SignInState(
    var emailIsInvalid: Boolean = false,
    var emailMessage: String = String(),
    var passwordIsInvalid: Boolean = false,
    var passwordMessage: String = String(),
    var isKeepConnected: Boolean = false,
)
