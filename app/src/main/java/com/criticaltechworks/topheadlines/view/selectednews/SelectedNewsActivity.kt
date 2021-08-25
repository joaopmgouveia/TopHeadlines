package com.criticaltechworks.topheadlines.view.selectednews

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.criticaltechworks.topheadlines.R
import com.criticaltechworks.topheadlines.contract.SelectedNewsContract
import com.criticaltechworks.topheadlines.databinding.SelectedNewsActivityBinding
import com.criticaltechworks.topheadlines.presentation.selectednews.createScene

class SelectedNewsActivity : AppCompatActivity(), SelectedNewsContract.View {

    override lateinit var presenter: SelectedNewsContract.Presenter
    private lateinit var binding: SelectedNewsActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = SelectedNewsActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        createScene()
        presenter.present(intent?.extras)
    }

    override fun setupNewsImage(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.ic_baseline_broken_image_24)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.selectedNewsImage)
    }

    override fun setupNewsTitle(title: String) {
        binding.selectedNewsTitle.text = title
    }

    override fun setupNewsDescription(description: String) {
        binding.selectedNewsDescription.text = description
    }

    override fun setupNewsContent(content: String) {
        binding.selectedNewsContent.text = content
    }

    override fun showGenericError() {
        binding.genericErrorLayout.visibility = View.VISIBLE
    }
}