package com.creditcard.data.models.signin

import kotlinx.serialization.SerialName

data class SignInRequest(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
)