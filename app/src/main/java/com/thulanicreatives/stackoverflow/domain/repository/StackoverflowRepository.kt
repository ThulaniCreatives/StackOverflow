package com.thulanicreatives.stackoverflow.domain.repository

import com.thulanicreatives.stackoverflow.domain.model.QuestionResults
import com.thulanicreatives.stackoverflow.util.Resource
import kotlinx.coroutines.flow.Flow

interface StackoverflowRepository {
    suspend fun getQuestionResult(): Flow<Resource<QuestionResults>>
}