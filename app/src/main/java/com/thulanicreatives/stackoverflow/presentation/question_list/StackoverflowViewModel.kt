package com.thulanicreatives.stackoverflow.presentation.question_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thulanicreatives.stackoverflow.domain.repository.StackoverflowRepository
import com.thulanicreatives.stackoverflow.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StackoverflowViewModel @Inject constructor(private val stackoverflowRepository: StackoverflowRepository) :
    ViewModel() {
    private val _mainState = MutableStateFlow(MainState())
    val mainState = _mainState.asStateFlow()

    //Question details state
    private val _questionDetailState = MutableStateFlow(QuestionDetailState())
    val questionDetailState = _questionDetailState.asStateFlow()
    private val searchJob: Job? = null

    fun onEvent(mainUIEvents: MainUIEvents) {
        when (mainUIEvents) {
            MainUIEvents.OnSearchClick -> {
                getAnswerResult()
            }
            is MainUIEvents.OnSearchQuestion -> {
                _mainState.update { it.copy(searchQuestion = mainUIEvents.newQuestion.lowercase()) }
            }
        }
    }

    fun onEventViewQuestionDetail(questionDetailEvents: QuestionDetailEvents) {
        when (questionDetailEvents) {
            is QuestionDetailEvents.OnViewQuestionDetail -> {
                _questionDetailState.update { it.copy(selectedId = questionDetailEvents.questionId) }
            }
            QuestionDetailEvents.OnQuestionItemClick -> getQuestionDetailResult()
        }
    }

    private fun getAnswerResult() {
        viewModelScope.launch {
            stackoverflowRepository.getQuestionResult(
                mainState.value.searchQuestion
            ).collect { result ->
                when(result) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> {
                        _mainState.update { it.copy(isLoading = result.isLoading) }
                    }
                    is Resource.Success -> {

                        result.data?.let { questionResults ->
                            _mainState.update { it.copy(questionResults = questionResults) }
                        }
                    }
                }
            }
        }
    }

    private fun getQuestionDetailResult() {
        viewModelScope.launch {
            stackoverflowRepository.getQuestionDetailResult(
                questionDetailState.value.selectedId
            ).collect { result ->
                when(result) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> {
                        _questionDetailState.update { it.copy(isLoading = result.isLoading) }
                    }
                    is Resource.Success -> {

                        result.data?.let { questionResults ->
                            _questionDetailState.update { it.copy(questionResults = questionResults) }

                        }

                    }
                }
            }
        }
    }

}