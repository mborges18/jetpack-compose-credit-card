package com.creditcard.features.authenticator.signin.data.model

import kotlinx.serialization.SerialName

data class SignInRequest(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
)