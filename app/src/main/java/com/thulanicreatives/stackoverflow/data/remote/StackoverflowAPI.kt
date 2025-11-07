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

    @GET("2.2/questions/{questionID}/answers?")
    suspend fun getQuestionDetailResult(
        @Path("questionID") questionID:String,
        @Query("order") order:String = "desc",
        @Query("sort") sort:String = "activity",
        @Query("site") site:String = "stackoverflow",
        @Query("filter") filter:String = "withbody",

        ): QuestionResultsDto?

//https://api.stackexchange.com/2.2/search/advanced?&pagesize=20&order=desc&sort=activity&title=&site=stackoverflow&filter=withbody

//https://api.stackexchange.com/2.2/questions/0/answers?&order=desc&sort=activity&site=stackoverflow&filter=withbody


    //https://api.stackexchange.com/2.2/questions/56435510/answers?order=desc&sort=activity&site=stackoverflow&filter=withbody
    companion object {
        const val BASE_URL = "https://api.stackexchange.com/"
    }
}