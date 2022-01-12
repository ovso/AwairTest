@file:Suppress("SpellCheckingInspection")

package io.github.ovso.schedule.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.github.ovso.schedule.data.EventUiModel
import io.github.ovso.schedule.utils.ScheduleItemCallback

class ScheduleAdapter : ListAdapter<EventUiModel, ScheduleViewHolder>(ScheduleItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder =
        ScheduleViewHolder.create(parent)

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.onBindViewHolder(
            getItem(position) ?: EventUiModel(null, null, null)
        )
    }
}