package com.hafidznrg.newsapp.adapter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hafidznrg.newsapp.R
import com.hafidznrg.newsapp.model.Article
import com.hafidznrg.newsapp.newsdetail.NewsDetailFragment

class NewsAdapter(private val newsList: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsImageView: ImageView = itemView.findViewById(R.id.newsImageView)
        val newsTitleTextView: TextView = itemView.findViewById(R.id.newsTitleTextView)
        val newsDescriptionTextView: TextView = itemView.findViewById(R.id.newsDescriptionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = newsList[position]
        holder.newsTitleTextView.text = article.title
        holder.newsDescriptionTextView.text = article.description
        Glide.with(holder.newsImageView.context)
            .load(article.urlToImage)
            .placeholder(R.mipmap.ic_image_not_found)
            .into(holder.newsImageView)

        val context = holder.newsImageView.context

        holder.itemView.setOnClickListener {
            val fragment = NewsDetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(NewsDetailFragment.ARTICLE_KEY, article)
            fragment.arguments = bundle

            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null) // Add this transaction to the back stack
                .commit()
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}
