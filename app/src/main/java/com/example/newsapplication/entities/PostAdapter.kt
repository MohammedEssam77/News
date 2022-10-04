package com.example.newsapplication.entities


import androidx.recyclerview.widget.DiffUtil

import com.example.newsapplication.R

class PostAdapter ( callback: PostItemClick , item: MutableList<News>):BaseAdapter<News>(item,callback) {

    companion object DiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame( oldItem: News,newItem: News): Boolean {
            return newItem.author + newItem.title == oldItem.author + newItem.title
        }

        }

    override val layoutId: Int
        get() = R.layout.news_item

}

interface PostItemClick:BaseListener {
    fun onClick(item: News)
}
