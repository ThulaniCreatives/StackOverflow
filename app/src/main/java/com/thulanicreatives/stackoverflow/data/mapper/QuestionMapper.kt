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
    acceptedAnswerId = accepted_answer_id ?: 0,
    answerCount = answer_count ?: 0,
    body = body ?: "",
    bountyAmount = bounty_amount ?: 0,
    bountyClosesDate = bounty_closes_date ?: 0,
    closedDate = bounty_closes_date ?: 0,
    contentLicense = content_license?: "",
    creationDate =creation_date ?: 0,
    isAnswered = false,
    lastActivityDate = last_activity_date ?: 0,
    lastEditDate = last_edit_date ?: 0,
    link = link ?: "",
    questionId = question_id ?: 0,
    score = score ?: 0,
    tags = tags ?: emptyList(),
    title = title ?: "",
    viewCount = view_count ?: 0

)




//val hasMore: Boolean,
//val items: List<Answer>,
//val quotaMax,
//val quotaRemaining