package com.example.emotiondiaryapp.database


import androidx.lifecycle.LiveData

class EmotionRepository(private val dao: EmotionDao) {

    val allEmotions: LiveData<List<EmotionEntry>> = dao.getAllEmotions()

    suspend fun insert(entry: EmotionEntry) {
        dao.insert(entry)
    }

    suspend fun delete(entry: EmotionEntry) {
        dao.delete(entry)
    }

    suspend fun getEmotionById(id: Int) = dao.getEmotionById(id)
}