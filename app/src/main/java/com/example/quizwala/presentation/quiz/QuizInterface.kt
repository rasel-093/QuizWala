package com.example.quizwala.presentation.quiz

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizwala.R
import com.example.quizwala.domain.repository.QuizInterface
import com.example.quizwala.presentation.quiz.component.QuizOption
import com.example.quizwala.presentation.util.Dimens
//@Preview
//@Composable
//fun QuizPrev(){
//    QuizInterface({}, 1, QuizState ,Modifier)
//}
@Composable
fun QuizInterface(
    onOptionSelected: (Int) -> Unit,
    questionNumber: Int,
    quizState: QuizState,
    modifier: Modifier
) {
    val question = quizState.quiz?.question?.replace("&quot;", "\"")?.replace("&#039", "\"")

    Box(modifier = modifier, contentAlignment = Alignment.Center){
        Column(modifier = Modifier.wrapContentHeight()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "$questionNumber .",
                    modifier = Modifier.weight(1f),
                    color = colorResource(
                        id = R.color.blue_gray
                    ),
                    fontSize = Dimens.MediumTextSize
                )

                Text(
                    text = question!!,
                    modifier = Modifier.weight(9f),
                    color = colorResource(
                        id = R.color.blue_gray
                    ),
                    fontSize = Dimens.MediumTextSize
                )
            }
            Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

            Column(modifier = Modifier.padding(horizontal = 15.dp)) {
                val options = listOf(
                    "A" to quizState.suffeledOptions[0].replace("&quot;", "\"").replace("&#039", "\""),
                    "B" to quizState.suffeledOptions[1].replace("&quot;", "\"").replace("&#039", "\""),
                    "C" to quizState.suffeledOptions[2].replace("&quot;", "\"").replace("&#039", "\""),
                    "D" to quizState.suffeledOptions[3].replace("&quot;", "\"").replace("&#039", "\""),
                )


                options.forEachIndexed() { index, (option, optionText) ->
                    if (optionText.isNotEmpty()) {
                        QuizOption(
                            optionNumber = option,
                            options = optionText,
                            selected = quizState.selectedOption == index,
                            onOptionClick = { onOptionSelected(index) }) {
                            onOptionSelected(-1)
                        }
                    }

                    Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
                }
            }
            Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))
        }
    }

}