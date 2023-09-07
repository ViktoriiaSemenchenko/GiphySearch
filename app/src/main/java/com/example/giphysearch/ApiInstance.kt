package com.example.giphysearch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.giphy.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }
}
