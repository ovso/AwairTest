package io.github.ovso.schedule.ui.info

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InfoModel(
    val end: String?,
    val start: String?,
    val title: String?
) : Parcelable