package com.example.quizwala.data.repository

import com.example.quizwala.data.remote.QuizApi
import com.example.quizwala.domain.model.Quiz
import com.example.quizwala.domain.repository.QuizInterface

class QuizRepoImpl(
    private val quizApi: QuizApi
) : QuizInterface {
    override suspend fun getQuizzes(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): List<Quiz> {
        return quizApi.getQuizzes(amount, category, difficulty, type).results
    }
}