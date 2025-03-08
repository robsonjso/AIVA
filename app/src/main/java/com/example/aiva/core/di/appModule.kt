package com.example.aiva.core.di

import android.util.Log
import com.example.aiva.BuildConfig
import com.example.aiva.core.data.remote.OpenAIService
import com.example.aiva.core.data.repository.GetAIResponseUseCase
import com.example.aiva.core.data.repository.OpenAIRepository
import com.example.aiva.core.data.repository.OpenAIRepositoryImpl
import com.example.aiva.core.viewmodel.ChatViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Interceptor para adicionar a chave da OpenAI
class OpenAIInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val apiKey = BuildConfig.OPENAI_API_KEY

        if (apiKey.isEmpty()) {
            throw IllegalStateException("🚨 API Key do OpenAI não foi definida! Verifique o gradle.properties")
        }

        Log.d("OpenAIInterceptor", "Chave carregada: ${apiKey.take(5)}****") // Log parcial da chave

        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $apiKey")
            .build()
        return chain.proceed(request)
    }
}

val appModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(OpenAIInterceptor()) // ✅ Usa o interceptor correto
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }
            }
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.openai.com/")
            .client(get()) // ✅ Obtém o OkHttpClient registrado acima
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(OpenAIService::class.java)
    }

    single<OpenAIRepository> { OpenAIRepositoryImpl(get()) }

    single { GetAIResponseUseCase(get()) }

    viewModel { ChatViewModel(get()) }
}


