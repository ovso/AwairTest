@file:Suppress("SpellCheckingInspection")

package io.github.ovso.schedule.ui.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.schedule.R
import io.github.ovso.schedule.data.EventUiModel
import io.github.ovso.schedule.databinding.ItemMainBinding
import io.github.ovso.schedule.ui.info.InfoActivity
import io.github.ovso.schedule.ui.info.InfoModel
import io.github.ovso.schedule.utils.ARGS
import java.lang.StringBuilder

class ScheduleViewHolder private constructor(
    private val binding: ItemMainBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBindViewHolder(item: EventUiModel) {
        binding.root.text = item.getContents()
        binding.root.setOnClickListener {
            with(Intent(it.context, InfoActivity::class.java)) {
                putExtra(ARGS, item.toInfoModel())
                it.context.startActivity(this)
            }
        }

        binding.root.setCompoundDrawablesWithIntrinsicBounds(
            if (item.conflict) R.drawable.ic_duplicate else 0, 0, 0, 0
        )
        binding.root.compoundDrawablePadding = if (item.conflict) 20 else 0
    }

    private fun EventUiModel.toInfoModel(): InfoModel = InfoModel(
        title = title,
        start = start,
        end = end
    )

    private fun EventUiModel.getContents(): StringBuilder {
        return StringBuilder()
            .append("[ ").append(title).append(" ]").append("\n")
            .append("start -> ").append(start).append("\n")
            .append("end -> ").append(end)
    }

    companion object {
        fun create(parent: ViewGroup): ScheduleViewHolder = ScheduleViewHolder(
            ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}