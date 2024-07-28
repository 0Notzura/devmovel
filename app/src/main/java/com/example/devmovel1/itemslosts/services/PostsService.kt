package com.example.devmovel1.itemslosts.services

import com.example.itemslosts.models.LostItem
import retrofit2.Response
import retrofit2.http.GET

interface PostsService {
    @GET("/api/posts")
    suspend fun getLostItems(): Response<List<LostItem>>

}
