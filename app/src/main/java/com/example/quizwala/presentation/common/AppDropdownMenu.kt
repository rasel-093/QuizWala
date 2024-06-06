package com.example.quizwala.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizwala.R
import com.example.quizwala.presentation.util.Dimens
import com.example.quizwala.presentation.util.Dimens.SmallSpacerHeight


@Preview
@Composable
fun DropdownPrev(){
    val menuList = listOf("A","B","C")
    AppDropdownMenu(menuName = "Dropdown", menuList = menuList, "", {} )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDropdownMenu(
    menuName: String,
    menuList: List<String>,
    text: String,
    onDropdownClick: (String)-> Unit
) {
    var  isExpanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.MediumPadding)
    ) {
        Text(
            text = menuName,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.blue_gray)
        )
        Spacer(modifier = Modifier.height(SmallSpacerHeight))

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = {isExpanded = !isExpanded}
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = {onDropdownClick(it)},
                readOnly = true,
                modifier = Modifier
                    .menuAnchor() //Necessary modifier
                    .fillMaxWidth(),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = colorResource(id = R.color.blue_gray),
                    unfocusedTrailingIconColor = colorResource(id = R.color.orange),
                    focusedTrailingIconColor = colorResource(id = R.color.orange),
                    focusedBorderColor = colorResource(id = R.color.dark_state_blue),
                    unfocusedBorderColor = colorResource(id = R.color.dark_state_blue),
                    containerColor = colorResource(id = R.color.dark_state_blue)
                ),
                shape = RoundedCornerShape(15.dp)
            )

            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.mid_night_blue)
                    )
            ) {
                menuList.forEachIndexed { index, s ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = s,
                                color = colorResource(id = R.color.blue_gray)
                            )
                        },
                        onClick = {
                            onDropdownClick(menuList[index])
                            isExpanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}