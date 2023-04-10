package com.example.booksearch.ui.recent

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booksearch.data.database.RecentQueryEntity
import com.example.booksearch.databinding.ItemRecentBinding

class RecentAdapter(
    private val onRecentClick: (String) -> Unit
) : RecyclerView.Adapter<RecentAdapter.RecentViewHolder>() {

    var recentQueryList: List<RecentQueryEntity> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentAdapter.RecentViewHolder {
        val binding = ItemRecentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: RecentAdapter.RecentViewHolder, position: Int) {
        with(holder.binding) {
            root.setOnClickListener { onRecentClick(recentQueryList[position].query) }
            textRecent.text = recentQueryList[position].query
        }
    }

    override fun getItemCount(): Int = recentQueryList.size

    inner class RecentViewHolder(val binding: ItemRecentBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root)
}
