package uz.botir.newsapp.core.network.service

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.botir.newsapp.core.model.everything.EverythingResponse
import uz.botir.newsapp.core.model.tophedline.TopHeadLineResponse

interface NewsService {

    @GET("v2/everything")
    fun getEverything(
        @Query("q") theme: String,
        @Query("apiKey") apiKey: String,
    ): Single<Response<EverythingResponse>>


    @GET("/v2/top-headlines")
    fun getTopheadline(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") size: Int = 50,
    ): Single<Response<TopHeadLineResponse>>


    @GET("/v2/top-headlines")
    fun getTopheadlineWithCategory(
        @Query("apiKey") apiKey: String,
        @Query("pageSize") size: Int = 50,
        @Query("category") category: String,
    ): Single<Response<TopHeadLineResponse>>


}