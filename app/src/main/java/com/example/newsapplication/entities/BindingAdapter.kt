package com.example.newsapplication.entities

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsapplication.R

@BindingAdapter("loadImage")
fun loadImage(view: AppCompatImageView, url: String?) {
    if (!url.isNullOrEmpty())
        Glide.with(view.context)
            .load(url)
            .apply(
                RequestOptions()
                    .error(R.drawable.ic_launcher_foreground)
            )
            .into(view)
}
