package com.example.quizwala.presentation.home

import HomeHeader
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.quizwala.presentation.common.AppDropdownMenu
import com.example.quizwala.presentation.common.ButtonBox
import com.example.quizwala.presentation.nav_graph.Routes
import com.example.quizwala.presentation.util.Constants
import com.example.quizwala.presentation.util.Dimens
import com.example.quizwala.presentation.util.Dimens.MediumPadding
import kotlin.reflect.KFunction1

//@Preview
//@Composable
//fun PreviewHomeScreen(){
//    HomeScreen(state = StateHomeScreen(), onEvent = {})
//}
@Composable
fun HomeScreen(
    state: StateHomeScreen,
    onEvent: (EventsHomeScreen)->Unit,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HomeHeader()

        Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))
        AppDropdownMenu(
            menuName = "Number of Questions",
            menuList = Constants.numbersAsString,
            text = state.numberOfQuizzes.toString()
        ) { onEvent(EventsHomeScreen.SetNumberOfQuizzes(it.toInt())) }
        Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
        AppDropdownMenu(
            menuName = "Category",
            menuList = Constants.categories,
            text = state.quizCategory
        ) { onEvent(EventsHomeScreen.SetQuizCategory(it)) }
        Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
        AppDropdownMenu(
            menuName = "Difficulty",
            menuList = Constants.difficulty,
            text = state.difficulty
        ) { onEvent(EventsHomeScreen.SetQuizDifficulty(it)) }
        Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
        AppDropdownMenu(
            menuName = "Type",
            menuList = Constants.type,
            text = state.quizType
        ) { onEvent(EventsHomeScreen.SetQuizType(it)) }
        Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

        ButtonBox(text = "Generate Quiz", padding = MediumPadding ){
//            Log.d("Quiz Details", "${state.numberOfQuizzes} ${state.quizCategory} ${state.difficulty} ${state.quizType}")
            navController.navigate(
                route = Routes.QuizScreen.passQuizParams(
                    quizNumber = state.numberOfQuizzes,
                    quizCategory = state.quizCategory,
                    quizDifficulty = state.difficulty,
                    quizType = state.quizType
                )
            )
            //println(Routes.QuizScreen.passQuizParams(state.numberOfQuizzes,state.quizCategory,state.difficulty,state.quizType))
        }
    }
}