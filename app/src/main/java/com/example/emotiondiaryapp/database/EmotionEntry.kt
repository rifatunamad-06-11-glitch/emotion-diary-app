package com.example.emotiondiaryapp.database


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emotions")
data class EmotionEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val emotion: String,
    val note: String?,
    val date: Long = System.currentTimeMillis()
)
