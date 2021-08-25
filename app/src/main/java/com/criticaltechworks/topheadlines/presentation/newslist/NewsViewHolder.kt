package com.criticaltechworks.topheadlines.presentation.newslist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.criticaltechworks.topheadlines.R
import com.criticaltechworks.topheadlines.databinding.NewsListItemBinding

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val viewBinding = NewsListItemBinding.bind(itemView)


    fun insertImage(imageUrl: String) {
        val imageView = viewBinding.newsImage
        Glide.with(imageView.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_baseline_broken_image_24)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(imageView)
    }
}
