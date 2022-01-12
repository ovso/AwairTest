package io.github.ovso.schedule.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.ovso.schedule.data.Event
import io.github.ovso.schedule.data.ScheduleRepository
import io.github.ovso.schedule.utils.TimeUtils
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ScheduleRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<Event>>(emptyList())
    val items: LiveData<List<Event>> = _items

    //    private var prevPageToken: String? = null
    private var nextPageToken: String? = null

    fun fetchEvents() = viewModelScope.launch {
        try {
            if ((nextPageToken == "").not()) {
                val data = repository.events(nextPageToken ?: "")
                _items.value = _items.value?.toMutableList()?.apply {
                    addAll(data.events.filter { it.title.isNullOrEmpty().not() })
                    sortBy { TimeUtils.stringToSystemMillis(it.start!!) }
                }
                nextPageToken = data.nextPageToken
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}