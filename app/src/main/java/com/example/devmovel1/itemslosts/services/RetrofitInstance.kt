package com.example.devmovel1.itemslosts.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://backend-devmovel.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: PostsService by lazy {
        retrofit.create(PostsService::class.java)
    }
}
