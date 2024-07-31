package com.example.devmovel1.itemslosts.services

import com.example.devmovel1.itemslosts.models.LoginResponse
import com.example.devmovel1.itemslosts.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/auth/register")
    suspend fun register(@Body userData: User): Response<LoginResponse>

    @POST("api/auth/login")
    suspend fun login(@Body userData: User): Response<LoginResponse>
}