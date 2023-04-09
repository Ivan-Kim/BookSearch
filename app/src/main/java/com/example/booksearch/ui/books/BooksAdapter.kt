package com.example.booksearch.ui.books

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booksearch.R
import com.example.booksearch.data.network.BookItem
import com.example.booksearch.databinding.ItemBookBinding

class BooksAdapter(private val onBookClick: (String) -> Unit) :
    PagingDataAdapter<BookItem, BooksAdapter.BooksViewHolder>(BookComparator) {

    companion object {
        object BookComparator : DiffUtil.ItemCallback<BookItem>() {
            override fun areItemsTheSame(oldItem: BookItem, newItem: BookItem): Boolean {
                return oldItem.link == newItem.link
            }

            override fun areContentsTheSame(oldItem: BookItem, newItem: BookItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BooksViewHolder {
        val binding = ItemBookBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false
        )
        return BooksViewHolder(binding, viewGroup.context)
    }

    override fun onBindViewHolder(viewHolder: BooksViewHolder, position: Int) {
        getItem(position)?.let { suggestion ->
            val context = viewHolder.context
            with(viewHolder.binding) {
                root.setOnClickListener { onBookClick(suggestion.link) }
                Glide.with(imgBook.context).load(suggestion.image).into(imgBook)
                txtTitle.text = context.getString(R.string.title, suggestion.title)
                txtAuthor.text = context.getString(R.string.author, suggestion.author)
                txtPublisher.text = context.getString(R.string.publisher, suggestion.publisher)
                txtPrice.text = context.getString(R.string.price, suggestion.discount.toString())
            }
        }
    }

    inner class BooksViewHolder(val binding: ItemBookBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root)
}
