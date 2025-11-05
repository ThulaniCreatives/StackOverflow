package com.thulanicreatives.stackoverflow.data.dto

data class AnswerDto(
    val acceptedAnswerId: Int? = null,
    val answerCount: Int? = null,
    val body: String? = null,
    val bountyAmount: Int? = null,
    val bountyClosesDate: Int? = null,
    val closedDate: Int? = null,
    val closedReason: String? = null,
    val contentLicense: String? = null,
    val creationDate: Int? = null,
    val isAnswered: Boolean? = null,
    val lastActivityDate: Int? = null,
    val lastEditDate: Int? = null,
    val link: String? = null,
    val questionId: Int? = null,
    val score: Int? = null,
    val title: String? = null,
    val viewCount: Int? = null
)