package io.github.ovso.schedule.data

data class EventUiModel(
    val end: String?,
    val start: String?,
    val title: String?,
    val conflict: Boolean = false
)