package com.thulanicreatives.stackoverflow.presentation.question_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.thulanicreatives.stackoverflow.R
import com.thulanicreatives.stackoverflow.domain.model.QuestionDetailData
import com.thulanicreatives.stackoverflow.presentation.component.QuestionListSection
import com.thulanicreatives.stackoverflow.presentation.component.SearchSection

@Composable
fun MainScreen(
    viewModel: StackoverflowViewModel = hiltViewModel(),
    //navController: NavHostController,
    onItemClicked: (QuestionDetailData) -> Unit
) {

    val mainState by viewModel.mainState.collectAsState()
    var index = 0
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (mainState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(45.dp)
                    .align(Alignment.Center),
                color = colorResource(R.color.stack_color)

            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 40.dp)
        ) {

            item { SearchSection(mainState, viewModel) }

            mainState.questionResults?.let { questionResults ->
                items(items = questionResults.items) { results ->
                    index++
                    val questionID = results.questionId.toString()
                    QuestionListSection(
                        results, index, questionResults.items.size, modifier = Modifier.clickable {
                            viewModel.onEventViewQuestionDetail(
                                QuestionDetailEvents.OnViewQuestionDetail(
                                    questionID
                                )
                            )
                            viewModel.onEventViewQuestionDetail(QuestionDetailEvents.OnQuestionItemClick)

                            val selectedData =
                                QuestionDetailData(results.questionId, results.link, results.title)
                            onItemClicked(selectedData)

                            //navController.navigate("QuestionDetail/$questionID")
                        })
                }
            }
        }

    }
}
