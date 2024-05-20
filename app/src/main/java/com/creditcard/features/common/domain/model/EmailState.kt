package com.creditcard.features.common.domain.model

data class EmailState(
    val value: String = String(),
    val message: String = String(),
    val hasError: Boolean = false,
)
