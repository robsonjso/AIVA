package com.example.aiva.core.data.repository

import com.example.aiva.core.data.model.ChatMessage


interface OpenAIRepository {
    suspend fun getAIResponse(prompt: String): String
}

