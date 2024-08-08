package com.example.devmovel1.itemslosts.models

import com.google.gson.annotations.SerializedName


data class AccountResponse2(
    @SerializedName("_id")
    val id: String,

    @SerializedName("ra")
    val ra: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("phoneNumber")
    val phoneNumber: String,

    @SerializedName("photoUrl")
    val photoUrl: String,

    @SerializedName("__v")
    val v: Int,
)
