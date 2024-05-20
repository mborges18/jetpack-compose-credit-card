package com.creditcard.features.authenticator.signin.di

import com.creditcard.features.authenticator.signin.data.api.CreditCardApi
import com.creditcard.features.authenticator.signin.data.api.CreditCardApiImpl
import com.creditcard.core.clientnetwork.KtorClient
import com.creditcard.features.authenticator.signin.data.repository.CreditCardRepository
import com.creditcard.features.authenticator.signin.data.repository.CreditCardRepositoryImpl
import com.creditcard.features.authenticator.signin.domain.usecase.SignInUseCase
import com.creditcard.features.authenticator.signin.domain.usecase.SignInUseCaseImpl
import com.creditcard.features.authenticator.signin.ui.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object SignInModule {
    val instance = module {
        single <CreditCardApi>{ CreditCardApiImpl(KtorClient) }
        factory <CreditCardRepository>{ CreditCardRepositoryImpl(api = get()) }
        factory <SignInUseCase>{ SignInUseCaseImpl(repository = get(), emailUseCase = get(), passwordUseCase = get()) }
        viewModel { SignInViewModel(signInUseCase = get(), emailUseCase = get(), passwordUseCase = get()) }
    }
}