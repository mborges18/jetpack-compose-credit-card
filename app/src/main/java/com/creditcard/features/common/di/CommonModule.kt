package com.creditcard.features.common.di

import com.creditcard.features.common.domain.usecase.ValidateEmailUseCase
import com.creditcard.features.common.domain.usecase.ValidateEmailUseCaseImpl
import com.creditcard.features.common.domain.usecase.ValidatePasswordUseCase
import com.creditcard.features.common.domain.usecase.ValidatePasswordUseCaseImpl
import org.koin.dsl.module

object CommonModule {
    val instance = module {
        factory <ValidateEmailUseCase>{ ValidateEmailUseCaseImpl() }
        factory <ValidatePasswordUseCase>{ ValidatePasswordUseCaseImpl() }
    }
}