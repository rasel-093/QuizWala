package com.example.quizwala.presentation.nav_graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quizwala.presentation.home.HomeScreen
import com.example.quizwala.presentation.home.HomeViewmodel
import com.example.quizwala.presentation.quiz.QuizScreen
import com.example.quizwala.presentation.quiz.QuizViewModel

@Composable
fun SetNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.HomeScreen.route ){

        composable(route = Routes.HomeScreen.route){
            val viewModel: HomeViewmodel = hiltViewModel()
            val state = viewModel.homeState.collectAsState().value
            HomeScreen(
                state = state,
                onEvent = viewModel::onEvent,
                navController = navController
            )
        }

        composable(
            route = Routes.QuizScreen.route,
//            route = "quiz_screen/{$ARG_KEY_QUIZ_NUMBER}/{$ARG_KEY_QUIZ_CATEGORY}/{$ARG_KEY_QUIZ_DIFFICULTY}/{$ARG_KEY_QUIZ_TYPE}",
            arguments = listOf(
                navArgument(ARG_KEY_QUIZ_NUMBER){ NavType.IntType},
                navArgument(ARG_KEY_QUIZ_CATEGORY){NavType.StringType},
                navArgument(ARG_KEY_QUIZ_DIFFICULTY){NavType.StringType},
                navArgument(ARG_KEY_QUIZ_TYPE){NavType.StringType}
            )
        ){
            val numOfQuiz = it.arguments?.getString(ARG_KEY_QUIZ_NUMBER)?.toInt()
            val category = it.arguments?.getString(ARG_KEY_QUIZ_CATEGORY)
            val difficulty = it.arguments?.getString(ARG_KEY_QUIZ_DIFFICULTY)
            val type = it.arguments?.getString(ARG_KEY_QUIZ_TYPE)

            val quizViewModel: QuizViewModel = hiltViewModel()
            val quizState by quizViewModel.quizList.collectAsState()

            QuizScreen(
                numOfQuiz = numOfQuiz!!,
                quizCategory = category!!,
                quizDifficulty = difficulty!!,
                quizType = type!!,
                event = {quizViewModel.onEvent(it)},
                state = quizState
            )
        }
    }
}