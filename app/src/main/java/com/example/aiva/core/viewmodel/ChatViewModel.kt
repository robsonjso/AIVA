package com.example.aiva.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aiva.core.data.repository.GetAIResponseUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChatViewModel(private val getAIResponseUseCase: GetAIResponseUseCase) : ViewModel() {

    private val _messages = MutableStateFlow<List<String>>(emptyList())
    val messages: StateFlow<List<String>> = _messages

    fun sendMessage(prompt: String) {
        viewModelScope.launch {
            val response = getAIResponseUseCase.execute(prompt)
            _messages.value = _messages.value + response
        }
    }
}


