package com.example.aiva.core.data.remote

import com.example.aiva.core.data.remote.model.OpenAIRequest
import com.example.aiva.core.data.remote.model.OpenAIResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface OpenAIService {
    @Headers("Content-Type: application/json")
    @POST("v1/chat/completions")
    suspend fun getChatResponse(@Body request: OpenAIRequest): OpenAIResponse
}
