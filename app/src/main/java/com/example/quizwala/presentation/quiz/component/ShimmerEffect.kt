package com.example.quizwala.presentation.quiz.component

import android.annotation.SuppressLint
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizwala.R
import com.example.quizwala.presentation.common.ButtonBox
import com.example.quizwala.presentation.util.Dimens

@Preview
@Composable
fun ShimmerEffectInterface(){
    Column(){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Box(modifier = Modifier
                .width(20.dp)
                .height(40.dp)
                .clip(MaterialTheme.shapes.medium)
                .weight(1f)
                .shimmerEffect()
            )
            Spacer(modifier = Modifier.width(5.dp))
            Box(modifier = Modifier
                .width(20.dp)
                .height(40.dp)
                .clip(MaterialTheme.shapes.medium)
                .weight(9f)
                .shimmerEffect()
            )
        }
        Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

        Column(modifier = Modifier.padding(horizontal = 15.dp)) {

            repeat(4){
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.MediumBoxHeight)
                    .clip(RoundedCornerShape(Dimens.LargeCornerRadius))
                    .shimmerEffect())

                Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
            }
        }
        Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

        Row(
            modifier = Modifier
                .padding(Dimens.MediumPadding)
                .navigationBarsPadding()
        ) {
            Box(
                modifier = Modifier
                    .padding(Dimens.SmallPadding)
                    .weight(0.43f)
                    .height(Dimens.MediumBoxHeight)
                    .width(40.dp)
                    .shimmerEffect()
            )
            Box(
                modifier = Modifier
                    .padding(Dimens.SmallPadding)
                    .weight(0.43f)
                    .height(Dimens.MediumBoxHeight)
                    .width(40.dp)
                    .shimmerEffect())
        }
    }
}
@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition(label = " ")
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = " "
    ).value
    background(color = colorResource(id = R.color.blue_gray).copy(alpha = alpha))
}