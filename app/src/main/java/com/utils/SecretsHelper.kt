package com.utils


import android.content.Context
import android.util.Log
import java.util.Properties

object SecretsHelper {
    fun getOpenAIKey(context: Context): String {
        val properties = Properties()
        context.assets.open("gradle.properties").use { properties.load(it) }
        val apiKey = properties.getProperty("OPENAI_API_KEY", "")

        Log.d("SecretsHelper", "API Key carregada: ${apiKey.take(4)}****") // Esconde parte da chave no log
        return apiKey
    }
}

