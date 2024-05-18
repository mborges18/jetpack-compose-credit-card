package com.creditcard.domain.models.authenticator.signin

data class SignInState(
    var emailIsInvalid: Boolean = false,
    var emailMessage: String = String(),
    var passwordIsInvalid: Boolean = false,
    var passwordMessage: String = String(),
    var isKeepConnected: Boolean = false,
)
