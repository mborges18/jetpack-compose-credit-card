package com.creditcard.features.common.domain.model

data class ValidateResult(
    val hasError: Boolean,
    val message: String = String()
)