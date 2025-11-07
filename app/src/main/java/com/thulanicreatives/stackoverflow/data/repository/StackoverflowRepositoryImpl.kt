package com.thulanicreatives.stackoverflow.data.repository

import android.app.Application
import com.thulanicreatives.stackoverflow.R
import com.thulanicreatives.stackoverflow.data.mapper.toQuestionResults
import com.thulanicreatives.stackoverflow.data.remote.StackoverflowAPI
import com.thulanicreatives.stackoverflow.domain.model.QuestionResults
import com.thulanicreatives.stackoverflow.domain.repository.StackoverflowRepository
import com.thulanicreatives.stackoverflow.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class StackoverflowRepositoryImpl @Inject constructor(
    private val stackoverflowAPI: StackoverflowAPI,
    private val application: Application
)  : StackoverflowRepository {
    override suspend fun getQuestionResult(question: String): Flow<Resource<QuestionResults>> {
        return flow {
            emit(Resource.Loading(true))

            val response = try {
                stackoverflowAPI.getQuestionResult(title= question)
            }catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(application.getString(R.string.network_error)))
                emit(Resource.Loading(false))
                return@flow
            }
            catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(application.getString(R.string.network_error)))
                emit(Resource.Loading(false))
                return@flow
            }
            catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(application.getString(R.string.network_error)))
                emit(Resource.Loading(false))
                return@flow
            }

            response?.let { questionDto ->
                emit(Resource.Success(questionDto.toQuestionResults()))
                emit(Resource.Loading(false))
                return@flow
            }


        }
    }

    override suspend fun getQuestionDetailResult(questionId: String): Flow<Resource<QuestionResults>> {
        return flow {
            emit(Resource.Loading(true))

            val response = try {
                stackoverflowAPI.getQuestionDetailResult(questionId)
            }catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(application.getString(R.string.network_error)))
                emit(Resource.Loading(false))
                return@flow
            }
            catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(application.getString(R.string.network_error)))
                emit(Resource.Loading(false))
                return@flow
            }
            catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(application.getString(R.string.network_error)))
                emit(Resource.Loading(false))
                return@flow
            }

            response?.let { questionDto ->
                emit(Resource.Success(questionDto.toQuestionResults()))
                emit(Resource.Loading(false))
                return@flow
            }

        }
    }

}