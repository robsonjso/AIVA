package com.example.aiva.core.ui.screens

import androidx.compose.runtime.Composable
import com.example.aiva.core.viewmodel.ChatViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatScreen(viewModel: ChatViewModel = koinViewModel()) {
    // Use o viewModel normalmente
}

