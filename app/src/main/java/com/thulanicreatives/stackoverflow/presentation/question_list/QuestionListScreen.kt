package com.thulanicreatives.stackoverflow.presentation.question_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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
        else if (mainState.errorMessage.isNotEmpty()) {
            ErrorView(mainState.errorMessage)
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
                        })
                }
            }
        }

    }
}

@Preview
@Composable
fun ErrorView(message: String = "Oops! Something went wrong, Please refresh after some time"){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Image(imageVector = Icons.Rounded.Info, contentDescription = null,
            modifier = Modifier.size(108.dp))
        Spacer(modifier = Modifier.padding(16.dp))
        ErrorText(message)
    }
}

@Composable
fun ErrorText(text: String, modifier: Modifier = Modifier){
    Text(text = text, modifier = modifier.fillMaxWidth().padding(horizontal = 48.dp),
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.error.copy(alpha = 0.9F))
}
