package com.thulanicreatives.stackoverflow.domain.model

import com.thulanicreatives.stackoverflow.data.dto.Owner
import java.time.LocalDateTime

data class Answer(
    val acceptedAnswerId: Int,
    val answerCount: Int,
    val body: String,
    val bountyAmount: Int,
    val bountyClosesDate: Int,
    val closedDate: Int,
    val contentLicense: String,
    val creationDate: Int,
    val isAnswered: Boolean,
    val lastActivityDate: Int,
    val lastEditDate: Int,
    val link: String,
    val owner: Owner,
    val questionId: Int,
    val score: Int,
    val tags: List<String>,
    val title: String,
    val viewCount: Int
)