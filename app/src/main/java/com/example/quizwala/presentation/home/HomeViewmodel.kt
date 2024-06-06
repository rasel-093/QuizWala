package com.example.quizwala.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewmodel: ViewModel(){

    private val _homeState = MutableStateFlow(StateHomeScreen())
    val homeState = _homeState
    fun onEvent(event: EventsHomeScreen){
        when(event){
            is EventsHomeScreen.SetNumberOfQuizzes ->{
                _homeState.value = homeState.value.copy(numberOfQuizzes = event.numberOfQuizzes)
            }
            is EventsHomeScreen.SetQuizCategory ->{
                _homeState.value = homeState.value.copy(quizCategory = event.category)
            }
            is EventsHomeScreen.SetQuizDifficulty ->{
                _homeState.value = homeState.value.copy(difficulty = event.difficulty)
            }
            is EventsHomeScreen.SetQuizType ->{
                _homeState.value = homeState.value.copy(quizType = event.type)
            }
            else->{

            }
        }
    }
}