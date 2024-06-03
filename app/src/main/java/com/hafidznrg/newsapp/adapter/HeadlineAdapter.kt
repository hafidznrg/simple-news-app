package com.hafidznrg.newsapp.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hafidznrg.newsapp.R
import com.hafidznrg.newsapp.model.Article
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class HeadlineAdapter(private val headlines: List<Article>) :
    RecyclerView.Adapter<HeadlineAdapter.HeadlineViewHolder>() {

    class HeadlineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headlineImageView: ImageView = itemView.findViewById(R.id.headlineImageView)
        val headlineTitleTextView: TextView = itemView.findViewById(R.id.headlineTitleTextView)
        val headlineAuthorTextView: TextView = itemView.findViewById(R.id.headlineAuthorTextView)
        val headlineDateTextView: TextView = itemView.findViewById(R.id.headlineDateTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_headline, parent, false)
        return HeadlineViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeadlineViewHolder, position: Int) {
        val article = headlines[position]
        val zoneDateTime = ZonedDateTime.parse(article.publishedAt)
        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        val formattedDate = zoneDateTime.format(formatter)
        holder.headlineTitleTextView.text = article.title
        holder.headlineAuthorTextView.text = article.source.name
        holder.headlineDateTextView.text = formattedDate
        Glide.with(holder.headlineImageView.context)
            .load(article.urlToImage)
            .into(holder.headlineImageView)

        val context = holder.headlineImageView.context

        holder.itemView.setOnClickListener {
            val browserIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
            context.startActivity(browserIntent)
        }
    }

    override fun getItemCount(): Int {
        return headlines.size
    }


}
