package com.vishal.app.di

import android.app.Application
import org.koin.android.ext.android.startKoin

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(networkModule, viewModelModule, databaseModule))
    }
}