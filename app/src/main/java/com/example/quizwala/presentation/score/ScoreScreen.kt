package com.example.quizwala.presentation.score

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quizwala.R
import com.example.quizwala.presentation.nav_graph.Routes
import com.example.quizwala.presentation.util.Dimens

//@Preview
//@Composable
//fun ScoreScreenPreview() {
//    ScoreScreen(numOfQuestions = 9, numOfCorrectAns = 8)
//}

@Composable
fun ScoreScreen(
    numOfQuestions: Int,
    numOfCorrectAns: Int,
    navController: NavController
) {
    BackHandler {
        navigateToHomeScreen(navController)
    }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.congrats))

    val score = "%.2f".format(((numOfCorrectAns / numOfQuestions.toFloat()) * 100)).toFloat()

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = colorResource(id = R.color.green))) {
            append(" $numOfCorrectAns ")
        }
        withStyle(style = SpanStyle(color = colorResource(id = R.color.black))) {
            append(" correct answers")
        }
        withStyle(style = SpanStyle(color = colorResource(id = R.color.black))) {
            append(" out of")
        }
        withStyle(style = SpanStyle(color = colorResource(id = R.color.green))) {
            append(" $numOfQuestions")
        }
        withStyle(style = SpanStyle(color = colorResource(id = R.color.black))) {
            append(" questions")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.MediumPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {
                //Navigate to homescreen
                navigateToHomeScreen(navController)
            }) {
                Icon(painter = painterResource(id = R.drawable.baseline_close_24), contentDescription = "Close" )
            }
        }

        Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(Dimens.SmallPadding)
                .clip(RoundedCornerShape(Dimens.MediumCornerRadius))
                .background(color = colorResource(id = R.color.white)),
            contentAlignment = Alignment.Center,

        ){

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LottieAnimation(
                    composition = composition,
                    iterations = 100,
                    modifier = Modifier.size(200.dp),
                )
                Text(
                    text = "Congrats!",
                    color = colorResource(id = R.color.green),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = Dimens.MediumTextSize
                )

                Text(
                    text = "$score % Score",
                    fontWeight = FontWeight.Bold,
                    fontSize = Dimens.LargeTextSize,
                    color = colorResource(
                        id = R.color.green
                    )
                )
                Text(text = "Quiz completed scuccessfully", fontWeight = FontWeight.Bold)
                Text(text = annotatedString, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
                Text(text = "Thank you for playing", fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Share with", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(Dimens.SmallSpacerHeight))
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.facebook),
                            contentDescription = "facebook"
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.instagram),
                            contentDescription = "instagram"
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.whatsapp),
                            contentDescription = "whatsapp"
                        )
                    }
                }

            }
        }
    }
}

fun navigateToHomeScreen(navController: NavController){
    navController.navigate(Routes.HomeScreen.route){
        popUpTo(Routes.HomeScreen.route){
            inclusive = true
        }
    }
}