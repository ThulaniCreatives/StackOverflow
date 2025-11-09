package com.thulanicreatives.stackoverflow.presentation.question_detail

import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.thulanicreatives.stackoverflow.R
import com.thulanicreatives.stackoverflow.domain.model.QuestionDetailData
import com.thulanicreatives.stackoverflow.presentation.question_list.StackoverflowViewModel

@Composable
fun QuestionDetailScreen(
    viewModel: StackoverflowViewModel = hiltViewModel(), questionDetailData: QuestionDetailData

) {
    val questionDetail by viewModel.questionDetailState.collectAsState()
    val selectedUri = questionDetailData.link

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (questionDetail.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(45.dp)
                    .align(Alignment.Center),
                color = colorResource(R.color.stack_color)

            )
        }
        else {
            QuestionDetailScreenWebView(selectedUri)
        }

    }

}
@Composable
fun QuestionDetailScreenWebView(url: String?) {
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }, update = {
        it.loadUrl(url ?: "https://stackoverflow.com/questions")
    })
}