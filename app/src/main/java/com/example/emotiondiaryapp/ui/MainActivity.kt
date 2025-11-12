package com.example.emotiondiaryapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emotiondiaryapp.database.EmotionAdapter
import com.example.emotiondiaryapp.databinding.ActivityMainBinding
import com.example.emotiondiaryapp.viewModel.EmotionViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: EmotionViewModel by viewModels()
    private lateinit var adapter: EmotionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView Adapter
        adapter = EmotionAdapter(emptyList()) { entry ->
            // Item click → open details
            val intent = Intent(this, EmotionDetailsActivity::class.java)
            intent.putExtra("emotion_id", entry.id)
            startActivity(intent)
        }

        binding.recyclerRecent.layoutManager = LinearLayoutManager(this)
        binding.recyclerRecent.adapter = adapter

        // Observe LiveData from ViewModel
        viewModel.allEmotions.observe(this) { list ->
            adapter.updateList(list)
        }

        // Click "Add Emotion" card → open AddEmotionActivity
        binding.cardAdd.setOnClickListener {
            startActivity(Intent(this, AddEmotionActivity::class.java))
        }

        // Click "View Diary" card → open AddEmotionActivity (or change to another activity if needed)
        binding.cardViewDiary.setOnClickListener {
            startActivity(Intent(this, AddEmotionActivity::class.java))
        }
    }
}
