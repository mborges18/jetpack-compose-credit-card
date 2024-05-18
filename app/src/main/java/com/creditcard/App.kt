package com.creditcard

import android.app.Application
import com.creditcard.features.authenticator.signin.di.SignInModule
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(SignInModule.instance)
        }
    }
}