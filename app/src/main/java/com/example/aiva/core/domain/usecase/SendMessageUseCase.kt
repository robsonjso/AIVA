package com.example.aiva.core.domain.usecase

import com.example.aiva.core.data.model.ChatMessage
import com.example.aiva.core.data.repository.ChatRepository

class SendMessageUseCase(private val repository: ChatRepository) {
    fun execute(message: ChatMessage) {
        repository.sendMessage(message)
    }
}
