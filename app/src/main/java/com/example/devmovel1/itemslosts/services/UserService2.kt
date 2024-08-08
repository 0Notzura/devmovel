package com.example.devmovel1.itemslosts.services

import com.example.devmovel1.itemslosts.models.AccountData
import com.example.devmovel1.itemslosts.models.AccountResponse
import com.example.devmovel1.itemslosts.models.AccountResponse2
import com.example.devmovel1.itemslosts.models.User
import com.example.itemslosts.models.LostItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService2 {

    @GET("/api/users/{id}")
    suspend fun getUserData(@Path("id") id: String): Response<AccountResponse2>
    companion object {
        private const val BASE_URL = "https://backend-devmovel.onrender.com"

        fun create(): PostsService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(PostsService::class.java)
        }
    }
}