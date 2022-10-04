package com.example.newsapplication.entities



import com.example.newsapplication.R

class PostAdapter ( callback: PostItemClick , item: MutableList<News>):BaseAdapter<News>(item,callback) {


    override val layoutId: Int
        get() = R.layout.news_item

}

interface PostItemClick:BaseListener {
    fun onClick(item: News)
}
