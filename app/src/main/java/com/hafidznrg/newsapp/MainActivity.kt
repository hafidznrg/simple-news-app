package com.hafidznrg.newsapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hafidznrg.newsapp.adapter.HeadlineAdapter
import com.hafidznrg.newsapp.adapter.NewsAdapter
import com.hafidznrg.newsapp.model.NewsResponse
import com.hafidznrg.newsapp.retrofit.ApiBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var headlinesRecyclerView: RecyclerView
    private lateinit var newsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        headlinesRecyclerView = findViewById(R.id.headlinesRecyclerView)
        newsRecyclerView = findViewById(R.id.newsRecyclerView)

        headlinesRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        newsRecyclerView.layoutManager = LinearLayoutManager(this)

        fetchNewsHeadlines()
        fetchAllNews()
    }

    private fun fetchNewsHeadlines() {
        val call = ApiBuilder.api.getTopHeadlines("us", "010faf1615754ebfa17ac7c1048e7914")

        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                Log.d("Headlines", "RESPONSE: ${response.body().toString()}")
                if (response.isSuccessful) {
                    val newsResponse = response.body()
                    if (newsResponse != null && newsResponse.status == "ok") {
                        val headlinesAdapter = HeadlineAdapter(newsResponse.articles)
                        headlinesRecyclerView.adapter = headlinesAdapter
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("Error", "Network request failed", t)
            }

        })
    }

    private fun fetchAllNews() {
        val call = ApiBuilder.api.getAllNews("indonesia", "010faf1615754ebfa17ac7c1048e7914")

        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                Log.d("All News", "RESPONSE: ${response.body()}")
                if (response.isSuccessful) {
                    val newsResponse = response.body()
                    if (newsResponse != null && newsResponse.status == "ok") {
                        val newsAdapter = NewsAdapter(newsResponse.articles)
                        newsRecyclerView.adapter = newsAdapter
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("Error", "Network request failed", t)
            }

        })
    }
}

