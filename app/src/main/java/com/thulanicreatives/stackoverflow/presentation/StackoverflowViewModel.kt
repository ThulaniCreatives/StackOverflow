package com.thulanicreatives.stackoverflow.presentation

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

    fun getAnswerResult() {
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
                        result.data?.let {
                            _mainState.update { it.copy(answer = it.answer) }
                        }
                    }
                }
            }
        }
    }


}
