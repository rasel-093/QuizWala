package com.example.quizwala.presentation.home

sealed class EventsHomeScreen{
    data class SetNumberOfQuizzes(val numberOfQuizzes: Int): EventsHomeScreen()
    data class SetQuizCategory(val category: String): EventsHomeScreen()
    data class SetQuizDifficulty(val difficulty: String): EventsHomeScreen()
    data class SetQuizType(val type: String): EventsHomeScreen()

}