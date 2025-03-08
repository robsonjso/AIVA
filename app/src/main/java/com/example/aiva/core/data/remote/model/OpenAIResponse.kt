package com.example.aiva.core.data.remote.model

data class OpenAIResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: Message
)
