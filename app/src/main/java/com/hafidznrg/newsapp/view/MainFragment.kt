package com.hafidznrg.newsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hafidznrg.newsapp.R
import com.hafidznrg.newsapp.adapter.HeadlineAdapter
import com.hafidznrg.newsapp.adapter.NewsAdapter
import com.hafidznrg.newsapp.databinding.MainFragmentBinding
import com.hafidznrg.newsapp.viewmodel.HeadlinesViewModel
import com.hafidznrg.newsapp.viewmodel.NewsViewModel

class MainFragment : Fragment() {

    private var _binding : MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val headlinesViewModel : HeadlinesViewModel by viewModels()
    private val newsViewModel : NewsViewModel by viewModels()

    private lateinit var headlinesAdapter: HeadlineAdapter
    private lateinit var newsAdapter: NewsAdapter

    private lateinit var headlineRecyclerView : RecyclerView
    private lateinit var newsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this._binding = MainFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = binding.root.context

        binding.headlinesRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(context)

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