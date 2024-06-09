package com.example.quizwala.presentation.quiz

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizwala.R
import com.example.quizwala.domain.repository.QuizInterface
import com.example.quizwala.presentation.common.ButtonBox
import com.example.quizwala.presentation.common.QuizAppBar
import com.example.quizwala.presentation.quiz.component.ShimmerEffectInterface
import com.example.quizwala.presentation.util.Constants
import com.example.quizwala.presentation.util.Dimens
import kotlinx.coroutines.launch

@Preview
@Composable
fun QuizScreenPreview() {
    QuizScreen(
        numOfQuiz = 5,
        quizCategory = "General Knowledge",
        quizDifficulty = "Medium",
        quizType = "Multiple Choice",
        event = {},
        state = StateQuizScreen()
        )
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizScreen(
    numOfQuiz: Int,
    quizCategory: String,
    quizDifficulty: String,
    quizType: String,
    event: (EventQuizScreen)->Unit,
    state: StateQuizScreen
) {
    val difficulty = quizDifficulty.lowercase()
    val type = when(quizType){
        "Multiple Choice" -> "multiple"
        else -> "boolean"

    }


    LaunchedEffect(key1 = Unit) {
        event(EventQuizScreen.GetQuizzes(numOfQuiz, Constants.categoriesMap[quizCategory]!!, difficulty, type))
    }


    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        QuizAppBar(quizCategory = quizCategory){
            //onback click -> Navigate to home screen
        }
        Column(
            modifier = Modifier
                .padding(Dimens.SmallPadding)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Questions: $numOfQuiz", color = colorResource(id = R.color.blue_gray))
                Text(text = quizDifficulty, color = colorResource(id = R.color.blue_gray))
                Text(text = quizCategory, color = colorResource(id = R.color.blue_gray))

            }

            Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.verySmallViewHeight)
                .clip(RoundedCornerShape(Dimens.MediumCornerRadius))
                .background(color = colorResource(id = R.color.blue_gray)))

            Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))



            if (isQuizFaced(state)){
                
                val pagerState = rememberPagerState{
                    state.quizState.size
                }
                HorizontalPager(state = pagerState) {index ->
                    QuizInterface(
                        questionNumber = index + 1,
                        onOptionSelected = { selectedIndex ->
                                           event(EventQuizScreen.SetOptionSelected(index, selectedIndex))
                        },
                        quizState = state.quizState[index],
                        modifier = Modifier.weight(1f)
                    )
                }

                val buttonText by remember {
                    derivedStateOf {
                        when(pagerState.currentPage){
                            0 -> { listOf("", "Next") }
                            state.quizState.size-1 -> { listOf("Previous", "Submit") }
                            else -> { listOf("Previous", "Next") }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(Dimens.MediumPadding)
                        .navigationBarsPadding()
                ) {
                    val scope = rememberCoroutineScope()

                    if (buttonText[0].isNotEmpty()){
                        ButtonBox(
                            text = "Previous",
                            padding = Dimens.SmallPadding,
                            fraction = 0.43f,
                            fontSize = Dimens.SmallTextSize
                        ) {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    }
                    else{
                        ButtonBox(
                            text = "",
                            padding = Dimens.SmallPadding,
                            fraction = 0.43f,
                            fontSize = Dimens.SmallTextSize,
                            containerColor = colorResource(id = R.color.mid_night_blue),
                            borderColor = colorResource(id = R.color.mid_night_blue)
                        ) {
                        }
                    }

                    ButtonBox(
                        text = buttonText[1],
                        padding = Dimens.SmallPadding,
                        fraction = 1f,
                        borderColor = colorResource(id = R.color.orange),
                        containerColor = if (pagerState.currentPage == state.quizState.size - 1) colorResource(id = R.color.orange) else colorResource(
                            id = R.color.dark_state_blue
                        ),
                        textColor = colorResource(id = R.color.white),
                        fontSize = Dimens.SmallTextSize
                    ) {
                        if (pagerState.currentPage == state.quizState.size - 1){
                            //submit
                            //Navigate to score screen
//                            state.quizState.forEach {
//                                Log.d("SelectedOptions", it.selectedOption.toString())
//                            }
                            Log.d("score", state.score.toString())
                        }else{
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun isQuizFaced(state: StateQuizScreen): Boolean{
    return when{
        state.isLoading -> {
            ShimmerEffectInterface()
            false
        }
        state.quizState.isNotEmpty() -> {
            true
        }
        else -> {
            Text(text = "No Quiz Found\n ${state.error}", color = colorResource(id = R.color.white))
            false
        }

    }
}