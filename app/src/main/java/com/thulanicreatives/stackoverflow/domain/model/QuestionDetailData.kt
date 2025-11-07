package com.thulanicreatives.stackoverflow.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class QuestionDetailData(
    val questionId: Int,
    val link: String,
    val title: String,
)
