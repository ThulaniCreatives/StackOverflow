package com.thulanicreatives.stackoverflow.presentation.question_list


import androidx.compose.runtime.Composable
import com.thulanicreatives.stackoverflow.domain.model.QuestionDetailData
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreenRoute

@Serializable
data class QuestionDetailRoute(
    val questionDetailData: QuestionDetailData
)