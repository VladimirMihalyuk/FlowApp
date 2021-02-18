package com.example.news.app.topheadlinesnews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.news.R
import kotlinx.android.synthetic.main.headline_news_item.view.*

class HeadlineNewsAdapter: PagingDataAdapter<TopHeadlineNewsPresentationModel, HeadlineNewsAdapter.ItemViewholder>(
    HeadlineNewsComparator
) {
    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.headline_news_item, parent, false)
        )
    }


    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: TopHeadlineNewsPresentationModel?) = with(itemView) {
            imageView.load(item?.image) {
                crossfade(true)
                error(R.drawable.news_handing)
            }
            textView.text = item?.title
            textView2.text = "${item?.author} | ${item?.time}"
            setOnClickListener {
                itemView.findNavController().navigate(TopHeadlineNewsFragmentDirections.actionTopHeadlinesNewsFragmentToNewsFragment(item?.url ?: "", item?.author ?: ""))
            }
        }
    }
}

object HeadlineNewsComparator : DiffUtil.ItemCallback<TopHeadlineNewsPresentationModel>() {
    override fun areItemsTheSame(oldItem: TopHeadlineNewsPresentationModel, newItem: TopHeadlineNewsPresentationModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TopHeadlineNewsPresentationModel, newItem: TopHeadlineNewsPresentationModel): Boolean {
        return oldItem == newItem
    }
}
