package com.example.booksearch.ui.recent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksearch.data.database.RecentQueryEntity
import com.example.booksearch.data.database.RecentQueryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentViewModel @Inject constructor(
    private val recentQueryRepository: RecentQueryRepository
) : ViewModel() {

    private var _recentQueryList = MutableStateFlow<List<RecentQueryEntity>>(emptyList())
    val recentQueryList = _recentQueryList.asStateFlow()

    init {
        viewModelScope.launch {
            _recentQueryList.value = recentQueryRepository.getAllRecentQuery()
        }
    }

}
