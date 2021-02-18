package com.example.news.app.choosecategory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.news.R
import com.example.news.app.search.SearchPresentationModel
import kotlinx.android.synthetic.main.headline_news_item.view.*

class ChoseCategoryAdapter: ListAdapter<ChoseCategoryPresentationModel, ChoseCategoryAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.headline_news_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun  bind(item: ChoseCategoryPresentationModel) = with(itemView) {
            imageView.load(item.image) {
                crossfade(true)
                error(R.drawable.news_handing)
            }
            textView.text = item.title
            textView2.text = "${item.author} | ${item.time}"
            setOnClickListener {
                itemView.findNavController().navigate(ChoseCategoryFragmentDirections.actionChoseCategoryFragmentToNewsFragment(item.url, item.author))
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<ChoseCategoryPresentationModel>() {
    override fun areItemsTheSame(
        oldItem: ChoseCategoryPresentationModel,
        newItem: ChoseCategoryPresentationModel
    ) = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: ChoseCategoryPresentationModel,
        newItem: ChoseCategoryPresentationModel
    ) = oldItem == newItem

}