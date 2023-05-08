package com.vishal.app.di

import com.vishal.app.Network.createBasicAuthService
import com.vishal.app.ViewModel.MainViewModel
import com.vishal.app.db.JokeDatabase
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module




val networkModule = module {
    single { createBasicAuthService() }
}

val viewModelModule = module {

    viewModel {
        MainViewModel(get(),get())
    }
}

val databaseModule = module {
    single { JokeDatabase.getInstance(get()) }
    single { get<JokeDatabase>().jokeDao() }
}