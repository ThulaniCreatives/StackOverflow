package com.thulanicreatives.stackoverflow.data.dto

data class AnswerDto(
    val accepted_answer_id: Int? = null,
    val answer_count: Int? = null,
    val body: String? = null,
    val bounty_amount: Int? = null,
    val bounty_closes_date: Int? = null,
    val content_license: String? = null,
    val creation_date: Int? = null,
    val is_answered: Boolean? = null,
    val last_activity_date: Int? = null,
    val last_edit_date: Int? = null,
    val link: String? = null,
    val owner: Owner? = null,
    val question_id: Int? = null,
    val score: Int? = null,
    val tags: List<String>? = null,
    val title: String? = null,
    val view_count: Int? = null
)