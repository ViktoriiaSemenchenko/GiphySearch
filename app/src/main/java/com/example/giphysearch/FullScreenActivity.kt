package com.example.giphysearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class FullScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fullscreen_layout)

        val gifImageView = findViewById<ImageView>(R.id.fullScreenGifImageView)

        val gifUrl = intent.getStringExtra("gif_url")

        if (!gifUrl.isNullOrEmpty()) {
            Glide.with(this)
                .asGif()
                .load(gifUrl)
                .into(gifImageView)
        }
    }
}