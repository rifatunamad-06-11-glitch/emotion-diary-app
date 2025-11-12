package com.example.emotiondiaryapp.database

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emotiondiaryapp.databinding.EmotionItemBinding
import java.text.SimpleDateFormat
import java.util.*

class EmotionAdapter(
    private var list: List<EmotionEntry>,
    private val onItemClick: (EmotionEntry) -> Unit
) : RecyclerView.Adapter<EmotionAdapter.EmotionViewHolder>() {

    inner class EmotionViewHolder(private val binding: EmotionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(entry: EmotionEntry) {
            binding.textViewEmotion.text = entry.emotion
            binding.textViewNote.text = entry.note ?: ""
            binding.textViewDate.text = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .format(Date(entry.date))

            binding.root.setOnClickListener { onItemClick(entry) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmotionViewHolder {
        val binding = EmotionItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EmotionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmotionViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateList(newList: List<EmotionEntry>) {
        list = newList
        notifyDataSetChanged()
    }
}

