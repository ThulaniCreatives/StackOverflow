package com.thulanicreatives.stackoverflow.data.mapper

import com.thulanicreatives.stackoverflow.data.dto.AnswerDto
import com.thulanicreatives.stackoverflow.data.dto.QuestionResultsDto
import com.thulanicreatives.stackoverflow.domain.model.Answer
import com.thulanicreatives.stackoverflow.domain.model.QuestionResults

fun QuestionResultsDto.toQuestionResults() = QuestionResults (
    hasMore = false ,
    items = items?.map {
        it.toAnswer()
    } ?: emptyList(),
    quotaMax = quotaMax ?: 0,
    quotaRemaining = quotaRemaining ?: 0
)


fun AnswerDto.toAnswer() = Answer(
    acceptedAnswerId = acceptedAnswerId ?: 0,
    answerCount = answerCount ?: 0,
    body = body ?: "",
    bountyAmount = bountyAmount ?: 0,
    bountyClosesDate = bountyClosesDate ?: 0,
    closedDate = closedDate ?: 0,
    closedReason = closedReason?: "",
    contentLicense = contentLicense?: "",
    creationDate = creationDate ?: 0,
    isAnswered = false,
    lastActivityDate = lastActivityDate ?: 0,
    lastEditDate = lastEditDate ?: 0,
    link = link ?: "",
    questionId = questionId ?: 0,
    score = score ?: 0,
    title = title ?: "",
    viewCount = viewCount ?: 0

)


//val hasMore: Boolean,
//val items: List<Answer>,
//val quotaMax,
//val quotaRemaining