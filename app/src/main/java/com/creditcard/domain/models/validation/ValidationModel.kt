package com.creditcard.domain.models.validation

import android.util.Patterns

object ValidationModel {
    fun String.isInValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches().not()
}