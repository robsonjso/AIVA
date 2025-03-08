package com.example.aiva.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.aiva.core.data.model.ChatMessage


@Dao
interface ChatDao {
    @Query("SELECT * FROM chatmessage ORDER BY timestamp DESC")
    fun getAllMessages(): List<ChatMessage>

    @Insert
    fun insertMessage(message: ChatMessage)
}
