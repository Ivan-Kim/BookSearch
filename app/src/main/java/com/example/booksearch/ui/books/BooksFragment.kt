package com.example.booksearch.ui.books

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksearch.databinding.FragmentBooksBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BooksFragment : Fragment() {

    companion object {
        fun newInstance() = BooksFragment()
    }

    private val viewModel: BooksViewModel by viewModels()

    private lateinit var binding: FragmentBooksBinding

    private lateinit var booksAdapter: BooksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBooksBinding.inflate(layoutInflater)
        setupSearchBar()
        setupBooksList()
        return binding.root
    }

    private fun setupSearchBar() {
        binding.buttonSearch.setOnClickListener {
            val query = binding.textSearch.text.toString()
            if (query.isNotBlank()) viewModel.updateBooks(query)
        }
    }

    private fun setupBooksList() {
        val onClickBook = { link: String ->
            val viewIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(viewIntent)

        }
        booksAdapter = BooksAdapter(onClickBook)
        binding.listBooks.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = booksAdapter
            addItemDecoration(DividerItemDecoration(context, SearchView.VERTICAL))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectSearchSuggestions()
    }

    private fun collectSearchSuggestions() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    booksAdapter.adapterState = it
                    booksAdapter.notifyDataSetChanged()
                }
            }
        }
    }

}