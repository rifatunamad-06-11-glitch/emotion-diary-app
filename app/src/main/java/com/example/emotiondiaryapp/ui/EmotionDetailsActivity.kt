package com.example.emotiondiaryapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.emotiondiaryapp.databinding.ActivityEmotionDetailsBinding
import com.example.emotiondiaryapp.viewModel.EmotionViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmotionDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmotionDetailsBinding
    private val viewModel: EmotionViewModel by viewModels()
    private var emotionId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEmotionDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the passed emotion ID from MainActivity
        emotionId = intent.getIntExtra("emotion_id", 0)

        // Load the emotion entry from DB
        loadEmotionDetails()

        // Delete button click
        binding.btnDelete.setOnClickListener {
            deleteEmotionEntry()
        }

        binding.imgBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadEmotionDetails() {
        lifecycleScope.launch(Dispatchers.IO) {
            val entry = viewModel.getEmotionById(emotionId)
            entry?.let {
                runOnUiThread {
                    binding.textViewDate.text =
                        android.text.format.DateFormat.format("yyyy-MM-dd HH:mm", it.date)
                    binding.textViewEmotion.text = it.emotion
                    binding.textViewNote.text = it.note ?: ""
                }
            }
        }
    }

    private fun deleteEmotionEntry() {
        lifecycleScope.launch(Dispatchers.IO) {
            val entryToDelete = viewModel.getEmotionById(emotionId)
            entryToDelete?.let {
                viewModel.delete(it)
                runOnUiThread {
                    finish() // Close activity and return to MainActivity
                }
            }
        }
    }
}
