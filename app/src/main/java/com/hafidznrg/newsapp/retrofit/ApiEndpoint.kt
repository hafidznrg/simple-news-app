package com.hafidznrg.newsapp.retrofit

import com.hafidznrg.newsapp.model.NewsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.OkHttpClient

interface NewsApiService {
    @GET("v2/top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsResponse>

    @GET("v2/everything")
    fun getAllNews(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsResponse>
}

object ApiBuilder {
    private const val BASE_URL = "https://newsapi.org/"
    private val okHttp = OkHttpClient.Builder()

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    private val retrofit = builder.build()

    val api: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}