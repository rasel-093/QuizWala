package com.example.quizwala.presentation.quiz

import com.example.quizwala.domain.model.Quiz

data class StateQuizScreen(
    val isLoading: Boolean = false,
    val data: List<Quiz>? = emptyList(),
    val error: String = ""
)