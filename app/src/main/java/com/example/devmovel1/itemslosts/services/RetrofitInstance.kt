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

    val posts: PostsService by lazy {
        retrofit.create(PostsService::class.java)
    }
    val auth: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }
    val auth2: AuthService2 by lazy {
        retrofit.create(AuthService2::class.java)
    }
    val user: UserService by lazy {
        retrofit.create(UserService::class.java)
    }
    val user2: UserService2 by lazy {
        retrofit.create(UserService2::class.java)
    }
}
