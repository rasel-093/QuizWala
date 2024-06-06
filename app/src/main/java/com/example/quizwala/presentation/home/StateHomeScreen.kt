package com.example.quizwala.presentation.home

data class StateHomeScreen(
    val numberOfQuizzes: Int = 10,
    val quizCategory: String = "General Knowledge",
    val difficulty: String = "Easy",
    val quizType: String = "Multiple Choice"
)
