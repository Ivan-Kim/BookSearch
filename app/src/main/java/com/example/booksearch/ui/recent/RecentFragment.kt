package com.example.booksearch.ui.recent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksearch.databinding.FragmentRecentBinding

class RecentFragment : Fragment() {

    private val viewModel: RecentViewModel by viewModels()

    private lateinit var binding: FragmentRecentBinding

    private lateinit var recentAdapter: RecentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecentBinding.inflate(layoutInflater)
        setupRecentList()
        return binding.root
    }

    private fun setupRecentList() {
        val onRecentClick = { recent: String ->
            val back = RecentFragmentDirections.actionRecentFragmentToBooksFragment(recent)
            findNavController().navigate(back)
        }
        recentAdapter = RecentAdapter(onRecentClick)
        binding.listRecent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recentAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }
}
