package com.vishal.app.Network

import com.vishal.app.Model.Joke
import retrofit2.http.GET

interface BasicApiService {
    @GET("api?format=json")
    suspend fun getPost():Joke
}