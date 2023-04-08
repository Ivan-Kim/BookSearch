package com.example.booksearch.ui.books

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booksearch.R
import com.example.booksearch.databinding.ItemBookBinding

class BooksAdapter(private val onBookClick: (String) -> Unit) :
    RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    var adapterState: BooksState = BooksState()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BooksViewHolder {
        val binding = ItemBookBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false
        )
        return BooksViewHolder(binding, viewGroup.context)
    }

    override fun onBindViewHolder(viewHolder: BooksViewHolder, position: Int) {
        val suggestion = adapterState.books[position]
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

    override fun getItemCount() = adapterState.books.size

    inner class BooksViewHolder(val binding: ItemBookBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root)
}
