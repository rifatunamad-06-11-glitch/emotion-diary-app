package com.example.emotiondiaryapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.emotiondiaryapp.database.EmotionDatabase
import com.example.emotiondiaryapp.database.EmotionEntry
import com.example.emotiondiaryapp.database.EmotionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmotionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EmotionRepository
    val allEmotions: LiveData<List<EmotionEntry>>

    init {
        val dao = EmotionDatabase.getDatabase(application).emotionDao()
        repository = EmotionRepository(dao)
        allEmotions = repository.allEmotions
    }

    fun insert(entry: EmotionEntry) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(entry)
    }

    fun delete(entry: EmotionEntry) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(entry)
    }

    suspend fun getEmotionById(id: Int) = repository.getEmotionById(id)

}
