package com.example.snapfood.presentation.ui.search

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchDebouncer @Inject constructor() {
    private val queryFlow = MutableStateFlow("")

    @OptIn(FlowPreview::class)
    fun getQueryFlow(
        debounceMs: Long = 1000
    ): Flow<String> = queryFlow.debounce(debounceMs)

    fun setQuery(query: String) {
        queryFlow.value = query
    }
}