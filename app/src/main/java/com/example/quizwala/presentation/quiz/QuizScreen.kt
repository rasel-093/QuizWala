package com.example.quizwala.presentation.quiz

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizwala.R
import com.example.quizwala.domain.repository.QuizInterface
import com.example.quizwala.presentation.common.ButtonBox
import com.example.quizwala.presentation.common.QuizAppBar
import com.example.quizwala.presentation.util.Constants
import com.example.quizwala.presentation.util.Dimens

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

            QuizInterface(
                questionNumber = 1,
                onOptionSelected = {},
                modifier = Modifier.weight(1f)
            )

            Row(
                modifier = Modifier
                    .padding(Dimens.MediumPadding)
                    .navigationBarsPadding()
            ) {
                ButtonBox(
                    text = "Previous",
                    padding = Dimens.SmallPadding,
                    fraction = 0.43f,
                    fontSize = Dimens.SmallTextSize
                ) {
                    
                }

                ButtonBox(
                    text = "Next",
                    padding = Dimens.SmallPadding,
                    fraction = 1f,
                    borderColor = colorResource(id = R.color.orange),
                    containerColor = colorResource(id = R.color.dark_state_blue),
                    textColor = colorResource(id = R.color.white),
                    fontSize = Dimens.SmallTextSize
                ) {

                }
            }
        }
    }

}