package com.creditcard.features.common.domain.model

data class PasswordState(
    val value: String = String(),
    val message: String = String(),
    val hasError: Boolean = false,
)
