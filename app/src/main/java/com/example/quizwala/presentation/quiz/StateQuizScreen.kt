package com.example.quizwala.presentation.quiz

import com.example.quizwala.domain.model.Quiz

data class StateQuizScreen(
    val isLoading: Boolean = false,
    val quizState: List<QuizState> = emptyList(),
    val error: String = "",
    val score: Int = 0
)

data class QuizState(
    val quiz: Quiz?,
    val suffeledOptions: List<String> = emptyList(),
    var selectedOption: Int? = -1,
)