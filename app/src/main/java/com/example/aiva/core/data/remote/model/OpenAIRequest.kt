package com.example.aiva.core.data.remote.model

data class OpenAIRequest(
    val model: String = "gpt-3.5-turbo", // Escolha do modelo da OpenAI
    val messages: List<Message>,
    val max_tokens: Int = 150,
    val temperature: Double = 0.7
)

data class Message(
    val role: String,  // "system", "user" ou "assistant"
    val content: String
)
