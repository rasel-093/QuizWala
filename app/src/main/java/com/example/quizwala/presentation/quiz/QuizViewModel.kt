package com.example.quizwala.presentation.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizwala.common.Resource
import com.example.quizwala.domain.usecases.GetQuizzesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QuizViewModel @Inject constructor(private val getQuizzesUseCases: GetQuizzesUseCases): ViewModel() {

    private val _quizList = MutableStateFlow(StateQuizScreen())
    val quizList = _quizList.asStateFlow()
    fun onEvent(event: EventQuizScreen){
        when(event){
            is EventQuizScreen.GetQuizzes ->{
                getQuizzes(event.numberOfQuizzes, event.category, event.difficulty, event.type)
            }
        }
    }

    private fun getQuizzes(amount: Int, category: Int, difficulty: String, type: String) {

        viewModelScope.launch {
            getQuizzesUseCases(amount, category, difficulty, type).collect{ resource ->
                when (resource) {
                    is Resource.Loading -> {
                        Log.d("Quiz", "Loading")
                        _quizList.value = StateQuizScreen(isLoading = true)
                    }
                    is Resource.Success ->{
                        _quizList.value = StateQuizScreen(data = resource.data)
                        for (quiz in resource.data!!){
                            Log.d("Quiz", quiz.toString())
                        }
                    }
                    is Resource.Error -> {
                        _quizList.value = StateQuizScreen(error = resource.message.toString())
                    }
                }
            }

        }
    }
}