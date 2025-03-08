package com.example.aiva.core.data.repository

import android.util.Log
import com.example.aiva.core.data.remote.OpenAIService
import com.example.aiva.core.data.remote.model.Message
import com.example.aiva.core.data.remote.model.OpenAIRequest
import com.example.aiva.core.data.remote.model.OpenAIResponse
import retrofit2.HttpException

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

            // Verifica se a resposta contém uma mensagem válida
            val aiResponse = response.choices.firstOrNull()?.message?.content ?: "Nenhuma resposta da IA."
            Log.d("OpenAIRepository", "Resposta da OpenAI: $aiResponse")
            aiResponse

        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            when (e.code()) {
                429 -> "Erro: Limite de requisições excedido. Verifique sua conta na OpenAI."
                401 -> "Erro: Chave de API inválida. Verifique suas credenciais."
                else -> "Erro da OpenAI: ${errorBody ?: e.message()}"
            }
        } catch (e: Exception) {
            Log.e("OpenAIRepository", "Erro ao acessar a OpenAI", e)
            "Erro ao acessar a OpenAI: ${e.message}"
        }
    }
}
