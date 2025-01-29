package com.bryukhanov.onlyofficetesttask

import android.app.Application
import com.bryukhanov.onlyofficetesttask.di.authModule
import com.bryukhanov.onlyofficetesttask.di.docModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(authModule, docModule)
        }
    }
}