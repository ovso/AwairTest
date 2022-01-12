@file:Suppress("SpellCheckingInspection")

package io.github.ovso.schedule.ui.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.schedule.R
import io.github.ovso.schedule.data.Event
import io.github.ovso.schedule.databinding.ItemMainBinding
import io.github.ovso.schedule.exts.stringResource
import io.github.ovso.schedule.ui.info.InfoActivity
import io.github.ovso.schedule.ui.info.InfoModel
import io.github.ovso.schedule.utils.ARGS
import java.lang.StringBuilder

class ScheduleViewHolder private constructor(
    private val binding: ItemMainBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBindViewHolder(item: Event) {
        binding.root.text = item.getContents()
        binding.root.setOnClickListener {
            with(Intent(it.context, InfoActivity::class.java)) {
                putExtra(ARGS, createModel(item))
                it.context.startActivity(this)
            }
        }
    }

    private fun createModel(item: Event) = InfoModel(
        title = item.title,
        start = item.start,
        end = item.end
    )

    private fun Event.getContents(): StringBuilder {
        return StringBuilder()
            .append("[ ").append(
                if (title.isNullOrBlank()) stringResource(R.string.cs) else title
            ).append(" ]").append("\n")
            .append("start -> ").append(start).append("\n")
            .append("end -> ").append(end)
    }

    companion object {
        fun create(parent: ViewGroup): ScheduleViewHolder = ScheduleViewHolder(
            ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}