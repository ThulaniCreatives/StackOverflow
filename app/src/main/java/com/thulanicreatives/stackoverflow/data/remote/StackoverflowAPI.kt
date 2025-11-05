package com.thulanicreatives.stackoverflow.data.remote

import com.thulanicreatives.stackoverflow.data.dto.QuestionResultsDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StackoverflowAPI {

    @GET("2.2/search/advanced?pagesize=20&order=desc&sort=activity&title={question} &site=stackoverflow&filter=withbody")
    suspend fun getQuestionResult(
        @Path("question") word:String
    ): QuestionResultsDto

    companion object {
        const val BASE_URL = "https://api.stackexchange.com/"
    }

}