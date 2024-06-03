package com.hafidznrg.newsapp.newsdetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.hafidznrg.newsapp.R
import com.hafidznrg.newsapp.databinding.MainFragmentBinding
import com.hafidznrg.newsapp.databinding.NewsDetailFragmentBinding
import com.hafidznrg.newsapp.model.Article
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class NewsDetailFragment : Fragment() {

    private var _binding : NewsDetailFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this._binding = NewsDetailFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener() {
            requireActivity().supportFragmentManager.popBackStack()
        }

        val article = arguments?.getParcelable<Article>(ARTICLE_KEY)

        binding.seeMoreButton.setOnClickListener() {
            val browserIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(article?.url))
            context!!.startActivity(browserIntent)
        }

        val zoneDateTime = ZonedDateTime.parse(article?.publishedAt)
        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        val formattedDate = zoneDateTime.format(formatter)

        binding.authorTextView.text = article?.source?.name
        binding.dateTextView.text = formattedDate
        binding.newsTitleTextView.text = article?.title
        binding.newsContentTextView.text = article?.content
        Glide.with(binding.newsImageView.context)
            .load(article?.urlToImage)
            .placeholder(R.mipmap.ic_image_not_found)
            .into(binding.newsImageView)
    }

    companion object {
        val ARTICLE_KEY = "article"

        fun newInstance(article: Article): NewsDetailFragment {
            val fragment = NewsDetailFragment()
            val args = Bundle()
            args.putParcelable(ARTICLE_KEY, article)
            fragment.arguments = args
            return fragment
        }
    }
}
