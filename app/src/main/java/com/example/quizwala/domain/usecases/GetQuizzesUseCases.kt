package com.example.quizwala.domain.usecases

import com.example.quizwala.common.Resource
import com.example.quizwala.data.repository.QuizRepoImpl
import com.example.quizwala.domain.model.Quiz
import com.example.quizwala.domain.repository.QuizInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetQuizzesUseCases(
    val quizRepository: QuizInterface
) {
    operator fun invoke(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): Flow<Resource<List<Quiz>>>  = flow{
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = quizRepository.getQuizzes(amount, category, difficulty, type)))
        }catch (e: Exception){
            emit(Resource.Error(message = e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}