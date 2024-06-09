package com.example.quizwala.presentation.nav_graph

import android.icu.text.MessagePattern.ArgType

const val ARG_KEY_QUIZ_NUMBER = "ak_quiz_number"
const val ARG_KEY_QUIZ_CATEGORY = "ak_quiz_category"
const val ARG_KEY_QUIZ_DIFFICULTY = "ak_quiz_difficulty"
const val ARG_KEY_QUIZ_TYPE = "ak_quiz_type"
const val NO_OF_QUESTIONS_KEY = "no_of_questions"
const val NO_OF_CORRECT_ANSWERS_KEY = "no_of_correct_answers"
sealed class Routes(val route: String){
    object HomeScreen : Routes("home_screen")
    object QuizScreen : Routes("quiz_screen/{$ARG_KEY_QUIZ_NUMBER}/{$ARG_KEY_QUIZ_CATEGORY}/{$ARG_KEY_QUIZ_DIFFICULTY}/{$ARG_KEY_QUIZ_TYPE}"){
        fun passQuizParams(quizNumber: Int, quizCategory: String, quizDifficulty: String, quizType: String): String{
            return "quiz_screen/{$ARG_KEY_QUIZ_NUMBER}/{$ARG_KEY_QUIZ_CATEGORY}/{$ARG_KEY_QUIZ_DIFFICULTY}/{$ARG_KEY_QUIZ_TYPE}"
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_NUMBER}",
                    newValue = quizNumber.toString()
                )
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_CATEGORY}",
                    newValue = quizCategory
                )
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_DIFFICULTY}",
                    newValue = quizDifficulty
                )
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_TYPE}",
                    newValue = quizType
                )
        }
    }

    object ScoreScreen: Routes("score_screen/{$NO_OF_QUESTIONS_KEY}/{$NO_OF_CORRECT_ANSWERS_KEY}"){
        fun passScoreParams(noOfQuestions: Int, noOfCorrectAnswers: Int): String{
            return "score_screen/{$NO_OF_QUESTIONS_KEY}/{$NO_OF_CORRECT_ANSWERS_KEY}".replace(
                oldValue = "{$NO_OF_QUESTIONS_KEY}",
                newValue = noOfQuestions.toString()
            ).replace(
                oldValue = "{$NO_OF_CORRECT_ANSWERS_KEY}",
                newValue = noOfCorrectAnswers.toString()
            )
        }
    }

}
