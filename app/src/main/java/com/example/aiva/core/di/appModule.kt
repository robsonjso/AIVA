package com.example.aiva.core.di

import com.example.aiva.core.data.repository.OpenAIRepository
import com.example.aiva.core.data.repository.OpenAIRepositoryImpl
import com.example.aiva.core.data.remote.OpenAIService
import com.example.aiva.core.data.repository.GetAIResponseUseCase
import com.example.aiva.core.viewmodel.ChatViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    // Interceptor para logs
    single {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    // Retrofit
    single {
        Retrofit.Builder()
            .baseUrl("https://api.openai.com/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenAIService::class.java)
    }

    // Repository
    single<OpenAIRepository> { OpenAIRepositoryImpl(get()) }

    // ViewModel
    viewModel { ChatViewModel(get()) }

    // UseCase (Adicione isso)
    single { GetAIResponseUseCase(get()) }


}
