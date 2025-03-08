package com.example.aiva.core.data.repository

import com.example.aiva.core.data.model.ChatMessage

interface ChatRepository {
    fun getChatHistory(): List<ChatMessage>
    fun sendMessage(message: ChatMessage)
}
