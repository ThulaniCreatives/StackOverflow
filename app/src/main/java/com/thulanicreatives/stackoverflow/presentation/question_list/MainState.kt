package com.thulanicreatives.stackoverflow.presentation.question_list

import com.thulanicreatives.stackoverflow.domain.model.QuestionResults

data class MainState(
    val isLoading: Boolean = false,
    val searchQuestion:String = "",
    val errorMessage:String = "",
    val questionResults: QuestionResults? = null
)

data class QuestionDetailState(
    val isLoading: Boolean = false,
    val selectedId:String = "",
    val questionResults: QuestionResults? = null
)