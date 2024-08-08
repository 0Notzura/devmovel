package com.example.devmovel1.itemslosts.services

import com.example.devmovel1.itemslosts.models.LoginResponse
import com.example.devmovel1.itemslosts.models.User
import com.example.devmovel1.itemslosts.models.User2
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService2 {
    @POST("api/auth/register")
    suspend fun register(@Body userData: User2): Response<LoginResponse>

    @POST("api/auth/login")
    suspend fun login(@Body userData: User): Response<LoginResponse>
}