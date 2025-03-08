package com.example.aiva.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "chatmessage")
data class ChatMessage(
    @SerializedName("id")
    @PrimaryKey val id: String,
    @SerializedName("sender")
    val sender: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("isFromAI")
    val isFromAI: Boolean
)
