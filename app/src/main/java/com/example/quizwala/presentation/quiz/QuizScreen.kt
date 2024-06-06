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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.quizwala.R
import com.example.quizwala.presentation.common.QuizAppBar
import com.example.quizwala.presentation.util.Dimens

@Composable
fun QuizScreen(
    numOfQuiz: Int,
    quizCategory: String,
    quizDifficulty: String,
) {
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
                Text(text = "Quiestions: $numOfQuiz", color = colorResource(id = R.color.blue_gray))
                Text(text = quizDifficulty, color = colorResource(id = R.color.blue_gray))
            }

            Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.verySmallViewHeight)
                .clip(RoundedCornerShape(Dimens.MediumCornerRadius))
                .background(color = colorResource(id = R.color.blue_gray)))

            Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))
        }
    }

}