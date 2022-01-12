package io.github.ovso.schedule.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.ovso.schedule.data.EventUiModel
import io.github.ovso.schedule.data.Events
import io.github.ovso.schedule.data.ScheduleRepository
import io.github.ovso.schedule.utils.TimeUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ScheduleRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<EventUiModel>>(emptyList())
    val items: LiveData<List<EventUiModel>> = _items

    private var nextPageToken: String? = null

    fun fetchEvents() = viewModelScope.launch(Dispatchers.IO) {
        try {
            if ((nextPageToken == "").not()) {
                val data = repository.events(nextPageToken ?: "")
                _items.postValue(updateAndGet(data))
                nextPageToken = data.nextPageToken
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun updateAndGet(data: Events): List<EventUiModel>? {
        return _items.value?.toMutableList()?.apply {
            addAll(data.events.filter { it.title.isNullOrEmpty().not() }.map {
                EventUiModel(
                    title = it.title,
                    start = it.start,
                    end = it.end,
                    conflict = false
                )
            })
            sortedBy { TimeUtils.stringToSystemMillis(it.start!!) }
            val startList = map {
                TimeUtils.stringToSystemMillis(it.start!!)
            }
            forEachIndexed { i, uiModel1 ->
                val frequency = Collections.frequency(
                    startList,
                    TimeUtils.stringToSystemMillis(uiModel1.start!!)
                )
                if (frequency > 1) {
                    set(i, uiModel1.copy(conflict = true))
                }
            }
        }
    }
}