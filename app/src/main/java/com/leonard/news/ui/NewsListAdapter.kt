package com.leonard.news.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.leonard.news.R
import com.leonard.news.databinding.NewsListItemBinding


class NewsListAdapter(private val onItemClicked : (newsUrl: String) -> Unit) : RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder>() {
    var newsList: List<UINewsItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val binding = DataBindingUtil.inflate<NewsListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.news_list_item,
            parent,
            false
        )
        return NewsListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        with(holder.binding) {
            val uiNewsItem = newsList[position]
            newsItem = uiNewsItem
            uiNewsItem.imageUrl?.let { imageUrl ->
                image.load(imageUrl) {
                    crossfade(true)
                }
            }

            root.setOnClickListener { onItemClicked(uiNewsItem.newsUrl) }
        }
    }

    class NewsListViewHolder(val binding: NewsListItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}