package com.hafidznrg.newsapp.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafidznrg.newsapp.adapter.HeadlineAdapter
import com.hafidznrg.newsapp.adapter.NewsAdapter
import com.hafidznrg.newsapp.databinding.ActivityMainBinding
import com.hafidznrg.newsapp.viewmodel.HeadlinesViewModel
import com.hafidznrg.newsapp.viewmodel.NewsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val headlinesViewModel : HeadlinesViewModel by viewModels()
    private val newsViewModel : NewsViewModel by viewModels()

    private lateinit var headlinesAdapter: HeadlineAdapter
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.headlinesRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(this)

        headlinesViewModel.getHeadlines("us")
        newsViewModel.getAllNews("indonesia")

        headlinesViewModel.articles.observe(this) {articles ->
            headlinesAdapter = HeadlineAdapter(articles)
            binding.headlinesRecyclerView.adapter = headlinesAdapter
        }

        newsViewModel.articles.observe(this) {articles ->
            newsAdapter = NewsAdapter(articles)
            binding.newsRecyclerView.adapter = newsAdapter
        }
    }




}

