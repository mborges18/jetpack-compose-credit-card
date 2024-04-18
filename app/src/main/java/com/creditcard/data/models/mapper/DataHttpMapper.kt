package com.creditcard.data.models.mapper

import com.creditcard.data.models.signin.SignInRequest
import com.creditcard.domain.models.authenticator.signin.SignInModel

fun SignInModel.toRequest() = SignInRequest(
    email = email,
    password = password
)