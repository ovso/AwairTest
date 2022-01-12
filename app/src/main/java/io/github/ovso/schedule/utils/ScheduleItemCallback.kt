package io.github.ovso.schedule.utils

import androidx.recyclerview.widget.DiffUtil

class ScheduleItemCallback<T> : DiffUtil.ItemCallback<T>() {
  override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
    oldItem == newItem

  override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
    areItemsTheSame(oldItem, newItem)
}