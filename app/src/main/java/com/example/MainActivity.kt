package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aiva.core.viewmodel.ChatViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val chatViewModel: ChatViewModel = koinViewModel()
            val messages by chatViewModel.messages.collectAsState()

            Column(Modifier.padding(16.dp)) {
                messages.forEach { message ->
                    Text(message)
                }

                var text by remember { mutableStateOf("") }

                BasicTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                )

                Button(onClick = {
                    chatViewModel.sendMessage(text)
                    text = ""
                }) {
                    Text("Enviar")
                }
            }
        }
    }
}
