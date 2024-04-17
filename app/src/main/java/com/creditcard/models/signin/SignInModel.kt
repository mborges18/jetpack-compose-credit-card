package com.creditcard.models.signin

import com.creditcard.models.validation.ValidationModel.isValidEmail

data class SignInModel(
    var email: String = String(),
    var password: String = String(),
    var isKeepConnected: Boolean = false,
) {
    fun isDataValid() =
        (email.isNotEmpty() && email.length > 5) &&
                (password.isNotEmpty() && password.length > 5 && password.length < 13)

    fun isEmailValid() = email.isValidEmail()
}
