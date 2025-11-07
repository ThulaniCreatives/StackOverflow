package com.thulanicreatives.stackoverflow.presentation.question_list

sealed class MainUIEvents {
    data class OnSearchQuestion(val newQuestion: String = "Java"): MainUIEvents()
    object OnSearchClick : MainUIEvents()
}