package com.thulanicreatives.stackoverflow.data.remote

import com.thulanicreatives.stackoverflow.data.dto.QuestionResultsDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StackoverflowAPI {

    @GET("2.2/search/advanced?")
    suspend fun getQuestionResult(
        @Query("pagesize") pageSize:Int = 20,
        @Query("order") order:String = "desc",
        @Query("sort") sort:String = "activity",
        @Query("title") title:String,
        @Query("site") site:String = "stackoverflow",
        @Query("filter") filter:String = "withbody",

        ): QuestionResultsDto?

    companion object {
        const val BASE_URL = "https://api.stackexchange.com/"
    }
}