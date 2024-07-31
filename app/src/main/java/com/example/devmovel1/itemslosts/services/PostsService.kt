package com.example.devmovel1.itemslosts.services

import com.example.itemslosts.models.LostItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface PostsService {
    @GET("/api/posts")
    suspend fun getLostItems(): Response<List<LostItem>>

    @GET("/api/posts/search")
    suspend fun searchLostItems(@Query("title") title: String): Response<List<LostItem>>
    @POST("/api/posts")
    fun createPost(
        @Header("x-auth-token") token: String, // Adiciona o token aqui
        @Body post: LostItem
    ): Call<LostItem>


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

