package com.thulanicreatives.stackoverflow.domain.model

data class QuestionResults(
    val hasMore: Boolean,
    val items: List<Answer>,
    val quotaMax: Int,
    val quotaRemaining: Int
)