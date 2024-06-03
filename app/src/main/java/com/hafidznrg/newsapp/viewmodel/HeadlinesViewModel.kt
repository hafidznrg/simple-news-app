package com.hafidznrg.newsapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hafidznrg.newsapp.model.Article
import com.hafidznrg.newsapp.model.NewsResponse
import com.hafidznrg.newsapp.retrofit.ApiBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeadlinesViewModel : ViewModel() {
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> get() = _articles

    private val API_KEY = "010faf1615754ebfa17ac7c1048e7914"

    fun getHeadlines(country: String) {
        ApiBuilder.api.getTopHeadlines(country, API_KEY)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if (response.isSuccessful) {
                        val newsResponse = response.body()
                        if (newsResponse != null && newsResponse.status == "ok") {
                            _articles.value = response.body()!!.articles
                        }
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.e("Error", "Network request failed", t)
                }
            })
    }
}