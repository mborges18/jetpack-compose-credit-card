package com.creditcard.features.authenticator.signin.domain.model

data class SignInModel(
    var email: String = String(),
    var password: String = String(),
    var isKeepConnected: Boolean = false,
)
