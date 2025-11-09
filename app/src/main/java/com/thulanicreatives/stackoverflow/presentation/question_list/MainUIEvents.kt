package com.thulanicreatives.stackoverflow.presentation.question_list

sealed class MainUIEvents {
    data class OnSearchQuestion(val newQuestion: String): MainUIEvents()
    object OnSearchClick : MainUIEvents()
    object OnClearQuestionResults : MainUIEvents()
}

sealed class QuestionDetailEvents {
    data class OnViewQuestionDetail(val questionId: String): QuestionDetailEvents()
    object OnQuestionItemClick : QuestionDetailEvents()
}