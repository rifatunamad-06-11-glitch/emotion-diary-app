package com.example.emotiondiaryapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.emotiondiaryapp.database.EmotionEntry
import com.example.emotiondiaryapp.databinding.ActivityAddEmotionBinding
import com.example.emotiondiaryapp.viewModel.EmotionViewModel

class AddEmotionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEmotionBinding
    private val viewModel: EmotionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddEmotionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val emotion = binding.editTextEmotion.text.toString().trim()
            val note = binding.editTextNote.text.toString().trim()

            if (emotion.isNotEmpty()) {
                val entry = EmotionEntry(emotion = emotion, note = note)
                viewModel.insert(entry)
                finish() // Go back to MainActivity
            }
        }

        binding.btnCancel.setOnClickListener {
            finish() // Back to MainActivity
        }
    }
}
