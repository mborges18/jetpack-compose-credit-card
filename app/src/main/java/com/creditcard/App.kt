package com.creditcard

import android.app.Application
import com.creditcard.di.AppModule
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(AppModule.instance)
        }
    }
}