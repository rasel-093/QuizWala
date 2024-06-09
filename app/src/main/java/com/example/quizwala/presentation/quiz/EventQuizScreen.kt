package com.example.quizwala.presentation.quiz

import com.example.quizwala.presentation.home.EventsHomeScreen

sealed class EventQuizScreen{
    data class GetQuizzes(
        val numberOfQuizzes: Int,
        val category: Int,
        val difficulty: String,
        val type: String
    ): EventQuizScreen()

    data class SetOptionSelected(val quizStateIndex: Int, val selectedOption: Int): EventQuizScreen()
}