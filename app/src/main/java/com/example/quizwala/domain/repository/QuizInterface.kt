package com.example.quizwala.domain.repository

import com.example.quizwala.domain.model.Quiz

interface QuizInterface {
    suspend fun getQuizzes(amount: Int, category: Int, difficulty: String, type: String): List<Quiz>
}