package com.example.quizwala.presentation.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizwala.common.Resource
import com.example.quizwala.domain.model.Quiz
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
            is EventQuizScreen.SetOptionSelected ->{
                updateQuizStateList(event.quizStateIndex, event.selectedOption)
            }
        }
    }

    private fun updateQuizStateList(quizStateIndex: Int, selectedOption: Int) {
//        quizList.value.quizState[quizStateIndex].selectedOption = selectedOption
//        _quizList.value = quizList.value.copy()

        val updatedQuizList = mutableListOf<QuizState>()
        quizList.value.quizState.forEachIndexed{index, quizState->
            updatedQuizList.add(
                if (quizStateIndex == index){
                    quizState.copy(selectedOption = selectedOption)
                }else{
                    quizState
                }
            )
        }
        _quizList.value = quizList.value.copy(quizState = updatedQuizList)

        updateScore(_quizList.value.quizState[quizStateIndex])
    }

    private fun updateScore(quizState: QuizState) {
        if (quizState.selectedOption != -1){
            val correctAnswer = quizState.quiz?.correct_answer
            val selectedOption = quizState.selectedOption?.let {
                quizState.suffeledOptions[it].replace("&quot;", "\"").replace("&#039", "\"")
            }
            Log.d("QuizViewModel", "updateScore: $correctAnswer $selectedOption")
            if (correctAnswer == selectedOption){
                _quizList.value = quizList.value.copy(score = _quizList.value.score + 1)
            }
        }
    }

    private fun getQuizzes(amount: Int, category: Int, difficulty: String, type: String) {

        viewModelScope.launch {
            getQuizzesUseCases(amount, category, difficulty, type).collect{ resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _quizList.value = StateQuizScreen(isLoading = true)
                    }
                    is Resource.Success ->{
                        val quizStateList: List<QuizState> = getQuizStateList(resource.data)
                        _quizList.value = StateQuizScreen(quizState =  quizStateList)
                    }
                    is Resource.Error -> {
                        _quizList.value = StateQuizScreen(error = resource.message.toString())
                    }
                }
            }

        }
    }

    private fun getQuizStateList(data: List<Quiz>?): List<QuizState> {
        val quizStateList: MutableList<QuizState> = mutableListOf<QuizState>()

        for (quiz in data!!) {
            val shuffledOptions: MutableList<String> = mutableListOf<String>().apply {
                add(quiz.correct_answer)
                addAll(quiz.incorrect_answers)
                shuffle()
            }

            quizStateList.add(QuizState(quiz, shuffledOptions, -1))
        }
        return quizStateList
    }
}