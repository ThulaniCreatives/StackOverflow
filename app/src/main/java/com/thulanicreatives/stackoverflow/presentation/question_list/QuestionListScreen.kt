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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thulanicreatives.stackoverflow.R
import com.thulanicreatives.stackoverflow.domain.model.QuestionDetailData
import com.thulanicreatives.stackoverflow.presentation.component.ErrorView
import com.thulanicreatives.stackoverflow.presentation.component.NetworkStatusScreen
import com.thulanicreatives.stackoverflow.presentation.component.QuestionListSection
import com.thulanicreatives.stackoverflow.presentation.component.SearchSection
import com.thulanicreatives.stackoverflow.presentation.network_connectivity.ConnectionViewModel
import com.thulanicreatives.stackoverflow.presentation.network_connectivity.NetworkViewModelFactory
import com.thulanicreatives.stackoverflow.util.AppConnectivityObserver

@Composable
fun MainScreen(
    viewModel: StackoverflowViewModel = hiltViewModel(), onItemClicked: (QuestionDetailData) -> Unit
) {
    val context = LocalContext.current
    val observer = remember { AppConnectivityObserver(context) }
    val networkViewModel: ConnectionViewModel = viewModel(
        factory = NetworkViewModelFactory(observer)
    )
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
        } else if (mainState.errorMessage.isNotEmpty()) {
            ErrorView(mainState.errorMessage)
        }
        NetworkStatusScreen(networkViewModel, mainState)
        val connected = networkViewModel.isConnected.collectAsState()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 40.dp)
        ) {
            item { SearchSection(mainState, viewModel) }
            mainState.questionResults?.let { questionResults ->
                if (connected.value) {
                    items(items = questionResults.items) { results ->
                        index++
                        val questionID = results.questionId.toString()
                        QuestionListSection(
                            results,
                            index,
                            questionResults.items.size,
                            modifier = Modifier.clickable {
                                viewModel.onEventViewQuestionDetail(
                                    QuestionDetailEvents.OnViewQuestionDetail(
                                        questionID
                                    )
                                )
                                viewModel.onEventViewQuestionDetail(QuestionDetailEvents.OnQuestionItemClick)

                                val selectedData = QuestionDetailData(
                                    results.questionId,
                                    results.link,
                                    results.title
                                )
                                onItemClicked(selectedData)
                            })
                    }
                }
            }
        }
    }
}
