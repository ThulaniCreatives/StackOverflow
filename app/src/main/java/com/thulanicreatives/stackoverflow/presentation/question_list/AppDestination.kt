package com.thulanicreatives.stackoverflow.presentation.question_list


import androidx.compose.runtime.Composable
import com.thulanicreatives.stackoverflow.domain.model.QuestionDetailData
import com.thulanicreatives.stackoverflow.presentation.question_detail.QuestionDetailScreen
import kotlinx.serialization.Serializable

interface AppDestination {
    val route: String
    val screen: @Composable () -> Unit
}

/**
 * stackoverflow app navigation destinations
 */
object QuestionDetailScreen: AppDestination {
    override val route = "questionDetails"
    override val screen: @Composable () -> Unit = {
        //QuestionDetailScreen(backStackStack)
    }
}

@Serializable
data object HomeScreen

@Serializable
data class QuestionDetail(
    val questionDetailData: QuestionDetailData
)