package io.github.ovso.schedule.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.ovso.schedule.data.Event
import io.github.ovso.schedule.data.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ScheduleRepository
) : ViewModel() {

    private var currentSearchResult: Flow<PagingData<Event>>? = null

    fun getEvents(): Flow<PagingData<Event>> {
        val lastResult = currentSearchResult
        if (lastResult != null) {
            return lastResult
        }
        val newResult: Flow<PagingData<Event>> = repository.events().cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}