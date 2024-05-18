package com.creditcard.core.validations

import android.util.Patterns

object Validation {
    fun String.isInValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches().not()
}