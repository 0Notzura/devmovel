package com.example.devmovel1.itemslosts.models

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("token")
    var authToken: String,
)