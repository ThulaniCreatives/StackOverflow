package com.thulanicreatives.stackoverflow.presentation

sealed class MainUIEvents{
    data class OnSearchQuestion(val newQuestion: String): MainUIEvents()
    object OnSearchClick : MainUIEvents()
}