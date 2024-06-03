//package com.hafidznrg.newsapp.newsdetail
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import com.bumptech.glide.Glide
//import com.hafidznrg.newsapp.R
//
//class NewsDetailFragment : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_news_detail, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        arguments?.let {
//            val title = it.getString("title")
//            val description = it.getString("description")
//            val content = it.getString("content")
//            val urlToImage = it.getString("urlToImage")
//
//            newsTitleTextView.text = title
//            newsDescriptionTextView.text = description
//            newsContentTextView.text = content
//            Glide.with(this).load(urlToImage).into(newsImageView)
//        }
//    }
//
//    companion object {
//        fun newInstance(article: Article) = NewsDetailFragment().apply {
//            arguments = Bundle().apply {
//                putString("title", article.title)
//                putString("description", article.description)
//                putString("content", article.content)
//                putString("urlToImage", article.urlToImage)
//            }
//        }
//    }
//}
