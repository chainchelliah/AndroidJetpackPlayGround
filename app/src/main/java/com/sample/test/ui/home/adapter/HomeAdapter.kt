package com.sample.test.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.test.R
import com.sample.test.db.entity.Home
import com.sample.test.utils.extension.format
import com.sample.test.utils.extension.inflate
import kotlinx.android.synthetic.main.item_home.view.*

/**
 * Created by SangiliPandian C on 22-11-2019.
 */
class HomeAdapter : ListAdapter<Home, HomeAdapter.ViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_home)) {
        fun bind(item: Home) {
            itemView.tvName.text = item.name
            itemView.tvPrice.text = item.price.format()
        }
    }
}

private class NewsDiffCallback : DiffUtil.ItemCallback<Home>() {
    override fun areItemsTheSame(oldItem: Home, newItem: Home): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Home, newItem: Home): Boolean =
        oldItem == newItem
}
