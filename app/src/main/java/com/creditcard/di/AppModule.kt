package com.creditcard.di

import com.creditcard.data.api.CreditCardApi
import com.creditcard.data.api.CreditCardApiImpl
import com.creditcard.data.client.KtorClient
import com.creditcard.data.repository.CreditCardRepository
import com.creditcard.data.repository.CreditCardRepositoryImpl
import com.creditcard.domain.models.authenticator.signin.SignInModel
import com.creditcard.domain.usecases.authenticator.signin.SignInUseCase
import com.creditcard.domain.usecases.authenticator.signin.SignInUseCaseImpl
import com.creditcard.ui.screens.authenticator.signin.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val instance = module {
        single { SignInModel() }
        single <CreditCardApi>{ CreditCardApiImpl(KtorClient) }
        factory <CreditCardRepository>{ CreditCardRepositoryImpl(api = get()) }
        factory <SignInUseCase>{ SignInUseCaseImpl(repository = get()) }
        viewModel { SignInViewModel(signInUseCase = get(), model = get()) }
    }
}