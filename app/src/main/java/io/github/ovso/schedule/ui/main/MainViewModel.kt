package io.github.ovso.schedule.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.ovso.schedule.data.Event
import io.github.ovso.schedule.data.ScheduleRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ScheduleRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<Event>>(emptyList())
    val items: LiveData<List<Event>> = _items
    private var nextPageToken: String? = null

    fun fetchEvents() = viewModelScope.launch {
        val data = repository.events()
        nextPageToken = data.nextPageToken
        _items.value = _items.value?.toMutableList()?.apply {
            addAll(data.events.filter { it.title.isNullOrEmpty().not() }.sortedBy { it.start })
        }
    }
}