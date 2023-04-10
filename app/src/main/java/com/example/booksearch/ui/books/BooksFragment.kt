package com.example.booksearch.ui.books

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksearch.R
import com.example.booksearch.databinding.FragmentBooksBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
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
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectSearchSuggestions()
        setupMenu()
        setUpInitialQuery()
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
                val recentMenu = menu.findItem(R.id.recentFragment)
                recentMenu.isVisible =
                    findNavController().currentDestination?.id == R.id.booksFragment
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.clear()
                menuInflater.inflate(R.menu.options_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return menuItem.onNavDestinationSelected(findNavController())
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setUpInitialQuery() {
        arguments?.getString("query")?.let { query ->
            if (query.isNotBlank()) {
                binding.textSearch.setText(query)
                viewModel.updateBooks(query)
            }
        }
    }

    private fun collectSearchSuggestions() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pagingDataFlow.collectLatest {
                    booksAdapter.submitData(it)
                }
            }
        }
    }
}
