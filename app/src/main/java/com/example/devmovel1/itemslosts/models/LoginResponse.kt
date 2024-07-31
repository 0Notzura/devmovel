package com.example.devmovel1.itemslosts.models

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("status_code")
    var statusCode: Int,

    @SerializedName("msg")
    var message: String,

    @SerializedName("auth_token")
    var authToken: String,

    @SerializedName("user")
    var user: User
)