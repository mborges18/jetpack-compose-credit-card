package com.creditcard.features.authenticator.signin.data.mapper

import com.creditcard.features.authenticator.signin.data.model.SignInRequest
import com.creditcard.features.authenticator.signin.domain.model.SignInModel

fun SignInModel.toRequest() = SignInRequest(
    email = email,
    password = password
)