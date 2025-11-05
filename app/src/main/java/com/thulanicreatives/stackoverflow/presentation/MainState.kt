package com.thulanicreatives.stackoverflow.presentation

import com.thulanicreatives.stackoverflow.domain.model.Answer

data class MainState(
    val isLoading: Boolean = false,
    val searchQuestion:String = "",
    val answer: Answer? = null

)
