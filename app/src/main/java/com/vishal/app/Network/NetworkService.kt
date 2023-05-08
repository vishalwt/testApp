package com.vishal.app.Network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private fun httpInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private fun basicOkHttpClient() = OkHttpClient.Builder().addInterceptor(httpInterceptor()).build()

fun createBasicAuthService() : BasicApiService =
    Retrofit.Builder()
        .baseUrl("https://geek-jokes.sameerkumar.website/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BasicApiService::class.java)