package com.example.quizwala.domain.di

import com.example.quizwala.data.remote.QuizApi
import com.example.quizwala.data.repository.QuizRepoImpl
import com.example.quizwala.domain.repository.QuizInterface
import com.example.quizwala.domain.usecases.GetQuizzesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {
    @Provides
    @Singleton
    fun provideGetQuizzesUseCases(quizInterface: QuizInterface): GetQuizzesUseCases{
        return GetQuizzesUseCases(quizInterface)
    }
}