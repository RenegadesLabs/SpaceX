package com.renegades.labs.spacex

import android.app.Application
import com.renegades.labs.spacex.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(module)
        }
    }
}