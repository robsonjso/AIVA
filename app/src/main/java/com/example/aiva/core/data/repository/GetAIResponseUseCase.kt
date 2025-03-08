package com.example.aiva.core.data.repository


class GetAIResponseUseCase(private val repository: OpenAIRepository) {
    suspend fun execute(prompt: String): String {
        return repository.getAIResponse(prompt)
    }
}
