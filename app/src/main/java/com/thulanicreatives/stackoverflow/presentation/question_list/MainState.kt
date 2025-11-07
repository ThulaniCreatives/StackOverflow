package com.thulanicreatives.stackoverflow.presentation.question_list

import com.thulanicreatives.stackoverflow.domain.model.Answer
import com.thulanicreatives.stackoverflow.domain.model.QuestionResults

data class MainState(
    val isLoading: Boolean = false,
    val searchQuestion:String = "",
    val questionResults: QuestionResults? = null
)