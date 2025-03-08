package com.example.aiva.core.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aiva.core.data.repository.OpenAIRepository
import com.example.aiva.core.data.repository.OpenAIRepositoryImpl

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel(private val repository: OpenAIRepository) : ViewModel()
{
    private val _messages = MutableStateFlow<List<String>>(emptyList())
    val messages: StateFlow<List<String>> = _messages

    fun sendMessage(userMessage: String) {
        _messages.value = _messages.value + "Você: $userMessage"

        viewModelScope.launch {
            try {
                val aiResponse = repository.getAIResponse(userMessage)
                _messages.value = _messages.value + "AIVA: $aiResponse"

                Log.d("ChatViewModel", "Resposta do OpenIA: $aiResponse")

            } catch (e: Exception) {
                _messages.value = _messages.value + "Erro ao obter resposta da IA."
                Log.e("ChatViewModel", "Erro ao consumir o Open IA", e)
            }
        }
    }
}





