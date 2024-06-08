package com.example.quizwala.data.remote.dto

import com.example.quizwala.domain.model.Quiz

data class QuizResponse(
    val response_code: Int,
    val results: List<Quiz>
)