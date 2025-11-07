package com.thulanicreatives.stackoverflow.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thulanicreatives.stackoverflow.R
import com.thulanicreatives.stackoverflow.presentation.question_list.MainState
import com.thulanicreatives.stackoverflow.presentation.question_list.MainUIEvents
import com.thulanicreatives.stackoverflow.presentation.question_list.StackoverflowViewModel

@Composable
fun SearchSection(mainState: MainState, stackoverflowViewModel: StackoverflowViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, top = 80.dp, end = 0.dp, bottom = 16.dp)
            .background(colorResource(R.color.stack_color)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
                .background(colorResource(R.color.stack_color))
                .clip(RoundedCornerShape(24.dp)),
            maxLines = 1,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White
            ),
            value = mainState.searchQuestion,
            onValueChange = {
                stackoverflowViewModel.onEvent(MainUIEvents.OnSearchQuestion(it))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = stringResource(R.string.search),
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            stackoverflowViewModel.onEvent(MainUIEvents.OnSearchClick)
                        })
            },
            label = {
                Text(
                    stringResource(R.string.search),
                    fontSize = 15.sp,
                    modifier = Modifier
                        .alpha(0.7f)
                        .padding(0.dp)
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp
            ),
        )
    }
}