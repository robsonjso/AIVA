package com.example.aiva.core.data.model

import com.google.gson.annotations.SerializedName

data class ChatContact(
    @SerializedName("name")
    val name: String,
    @SerializedName("avatarUrl")
    val avatarUrl: String,
    @SerializedName("isActive")
    val isActive: Boolean
)
