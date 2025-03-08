package com.example.aiva.core.domain.usecase

import com.example.aiva.core.data.model.ChatContact
import com.example.aiva.core.data.repository.ChatRepository

class GetContactsUseCase(private val repository: ChatRepository) {
    fun execute(): List<ChatContact> {
        return listOf() // Implementar chamada ao repositório
    }
}
