package io.github.ovso.schedule.exts

import android.content.Context
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView


fun Context.stringResource(@StringRes id: Int): String {
    return resources.getString(id)
}

fun RecyclerView.ViewHolder.stringResource(@StringRes id: Int): String {
    return itemView.context.stringResource(id)
}