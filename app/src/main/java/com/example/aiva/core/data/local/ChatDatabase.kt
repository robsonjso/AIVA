package com.example.aiva.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aiva.core.data.model.ChatMessage

@Database(entities = [ChatMessage::class], version = 1)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao
}
