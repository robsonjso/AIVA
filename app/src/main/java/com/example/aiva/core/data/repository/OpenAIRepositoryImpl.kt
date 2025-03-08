package com.example.aiva.core.data.repository

import com.example.aiva.core.data.remote.OpenAIService
import com.example.aiva.core.data.remote.model.Message
import com.example.aiva.core.data.remote.model.OpenAIRequest
import com.example.aiva.core.data.remote.model.OpenAIResponse


class OpenAIRepositoryImpl(private val openAIService: OpenAIService) : OpenAIRepository {
    override suspend fun getAIResponse(prompt: String): String {
        return try {
            val request = OpenAIRequest(
                messages = listOf(
                    Message("system", "You are a helpful assistant."),
                    Message("user", prompt)
                )
            )
            val response: OpenAIResponse = openAIService.getChatResponse(request)
            response.choices.firstOrNull()?.message?.content ?: "No response from AI."
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }
}
