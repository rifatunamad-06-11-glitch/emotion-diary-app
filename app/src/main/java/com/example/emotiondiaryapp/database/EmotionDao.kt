package com.example.emotiondiaryapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EmotionDao {

    @Query("SELECT * FROM emotions ORDER BY date DESC")
    fun getAllEmotions(): LiveData<List<EmotionEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(emotion: EmotionEntry)

    @Delete
    suspend fun delete(emotion: EmotionEntry)

    @Query("SELECT * FROM emotions WHERE id = :id")
    suspend fun getEmotionById(id: Int): EmotionEntry?
}
