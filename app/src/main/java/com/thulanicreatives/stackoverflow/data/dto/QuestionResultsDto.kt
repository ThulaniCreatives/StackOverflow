package com.thulanicreatives.stackoverflow.data.dto

data class QuestionResultsDto(
    val hasMore: Boolean = false,
    val items: List<AnswerDto>? = null,
    val quotaMax: Int? = null,
    val quotaRemaining: Int? = null
)