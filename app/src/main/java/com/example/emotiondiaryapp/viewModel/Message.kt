package com.example.emotiondiaryapp.viewModel

data class Message(
    var senderId: String? = null,
    var senderName: String? = null,
    var messageText: String? = null,
    var timestamp: Long = 0L // use epoch millis
)