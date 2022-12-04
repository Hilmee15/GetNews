package com.hilmi.latihangetjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.hilmi.latihangetjson.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val NEWS_DATA = "news"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        val newsData = intent.getParcelableExtra<Tech>(NEWS_DATA)

        if (newsData != null) {
            binding.apply {
                tvTitle.text = newsData.title
                tvAuthor.text = newsData.author
                tvTime.text = newsData.time
                tvDesc.text = newsData.desc
                Glide.with(this@DetailActivity)
                    .load(newsData.thumb)
                    .into(ivThumb)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
