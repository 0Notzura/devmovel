package com.example.devmovel1.itemslosts.services

import com.example.itemslosts.models.LostItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsService {
    @GET("/api/posts")
    suspend fun getLostItems(): Response<List<LostItem>>

    @GET("/api/posts/search")
    suspend fun searchLostItems(@Query("title") title: String): Response<List<LostItem>>
}

