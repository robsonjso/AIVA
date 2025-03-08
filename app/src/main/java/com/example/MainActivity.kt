package com.example

import com.example.aiva.core.viewmodel.ChatViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val chatViewModel: ChatViewModel = koinViewModel()
            val messages by chatViewModel.messages.collectAsState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Exibe as mensagens
                messages.forEach { message ->
                    Text(message)
                }

                Spacer(modifier = Modifier.height(16.dp))

                var text by remember { mutableStateOf("") }

                BasicTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions(onSend = {
                        if (text.isNotEmpty()) {
                            chatViewModel.sendMessage(text)
                            text = "" // Limpa o campo após envio
                        }
                    })
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = {
                    if (text.isNotEmpty()) {
                        chatViewModel.sendMessage(text)
                        text = ""
                    }
                }) {
                    Text("Enviar")
                }
            }
        }
    }
}

