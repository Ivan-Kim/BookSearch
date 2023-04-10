package com.example.booksearch.ui.recent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksearch.databinding.FragmentRecentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectRecentQueryList()
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

    private fun collectRecentQueryList() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.recentQueryList.collectLatest {
                    recentAdapter.recentQueryList = it
                    recentAdapter.notifyDataSetChanged()
                }
            }
        }
    }

}
