package com.criticaltechworks.topheadlines.view.newslist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.criticaltechworks.topheadlines.News
import com.criticaltechworks.topheadlines.NewsEvent
import com.criticaltechworks.topheadlines.contract.NewsListContract
import com.criticaltechworks.topheadlines.databinding.NewsListActivityBinding
import com.criticaltechworks.topheadlines.presentation.newslist.NewsAdapter
import com.criticaltechworks.topheadlines.presentation.newslist.createScene
import io.reactivex.subjects.PublishSubject

class NewsListActivity : AppCompatActivity(), NewsListContract.View {

    override lateinit var presenter: NewsListContract.Presenter
    override lateinit var adapter: NewsAdapter
    override lateinit var uiClickListener: PublishSubject<NewsEvent>
    private lateinit var binding: NewsListActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = NewsListActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        createScene()
        binding.newsList.adapter = adapter
        binding.newsList.layoutManager = LinearLayoutManager(this)
        presenter.present()
    }

    override fun setupNewsProviderTitle(title: String) {
        binding.newsProviderTitle.text = title
    }

    override fun populateRecyclerView(news: List<News>) {
        binding.newsList.visibility = View.VISIBLE
        adapter.add(news)
    }

    override fun showNoNews() {
        binding.newsList.visibility = View.GONE
        binding.errorLayout.visibility = View.VISIBLE
    }

    override fun showLoading() {
        binding.loadingLayout.loadingLayout.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.loadingLayout.loadingLayout.visibility = View.GONE
    }

    override fun newsItemClicked(): PublishSubject<NewsEvent> {
        return uiClickListener
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.cleanDisposables()
    }
}