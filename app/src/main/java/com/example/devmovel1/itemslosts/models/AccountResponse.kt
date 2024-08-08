package com.example.devmovel1.itemslosts.models

import com.google.gson.annotations.SerializedName

data class AccountResponse(
    @SerializedName("_id")
    val id: String,

    @SerializedName("ra")
    val ra: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("__v")
    val v: Int,
)
