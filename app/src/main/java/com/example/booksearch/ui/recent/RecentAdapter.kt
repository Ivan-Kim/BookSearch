package com.example.booksearch.ui.recent

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booksearch.databinding.ItemRecentBinding

class RecentAdapter(private val onRecentClick: (String) -> Unit) :
    RecyclerView.Adapter<RecentAdapter.RecentViewHolder>() {

    private var recents: List<String> = listOf("cat", "dog", "chicken") // dummy test

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentAdapter.RecentViewHolder {
        val binding = ItemRecentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: RecentAdapter.RecentViewHolder, position: Int) {
        with(holder.binding) {
            root.setOnClickListener { onRecentClick(recents[position]) }
            textRecent.text = recents[position]
        }
    }

    override fun getItemCount(): Int = recents.size

    inner class RecentViewHolder(val binding: ItemRecentBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root)
}
