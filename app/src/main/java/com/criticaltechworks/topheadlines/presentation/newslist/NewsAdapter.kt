package com.criticaltechworks.topheadlines.presentation.newslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.criticaltechworks.topheadlines.News
import com.criticaltechworks.topheadlines.NewsEvent
import com.criticaltechworks.topheadlines.R
import io.reactivex.subjects.PublishSubject

class NewsAdapter(
    private var newsList: List<News>,
    private val uiClickListener: PublishSubject<NewsEvent>
) : RecyclerView.Adapter<NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.news_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.insertImage(newsList[position].imageUrl)
        holder.viewBinding.newsHeadline.text = newsList[position].title
        holder.viewBinding.newsItemLayout.setOnClickListener {
            uiClickListener.onNext(NewsEvent(newsList[position]))
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun add(news: List<News>) {
        val newSize = newsList.size + news.size
        newsList = news
        notifyItemRangeInserted(newSize, news.size)
    }
}