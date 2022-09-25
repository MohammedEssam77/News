package com.example.newsapplication.entities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.databinding.NewsItemBinding

class PostAdapter (val callback: PostItemClick) : ListAdapter<News, PostAdapter.PostViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame( oldItem: News,newItem: News): Boolean {
            return newItem.author + newItem.title == oldItem.author + newItem.title
        }
    }

    class PostViewHolder(val viewDataBinding: NewsItemBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(listener: PostItemClick, posts: News) {
            viewDataBinding.news= posts
            viewDataBinding.itemclick = listener
            viewDataBinding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): PostViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NewsItemBinding.inflate(layoutInflater, parent, false)
                return PostViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.viewDataBinding.also {
            holder.bind(callback, getItem(position))
        }
    }
}

class PostItemClick(val block: (News) -> Unit) {
    fun onClick(item: News) = block(item)
}