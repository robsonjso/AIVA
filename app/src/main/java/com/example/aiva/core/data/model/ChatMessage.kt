package com.example.aiva.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chatmessage")
data class ChatMessage(
    @PrimaryKey val id: String,
    val sender: String,
    val message: String,
    val timestamp: Long,
    val isFromAI: Boolean
)
