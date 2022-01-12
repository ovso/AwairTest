package io.github.ovso.schedule.ui.info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.ovso.schedule.R
import io.github.ovso.schedule.databinding.ActivityInfoBinding
import io.github.ovso.schedule.exts.stringResource
import io.github.ovso.schedule.utils.ARGS
import io.github.ovso.schedule.utils.viewBinding
import java.lang.StringBuilder

class InfoActivity : AppCompatActivity() {
    private val binding by viewBinding<ActivityInfoBinding>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getParcelableExtra<InfoModel>(ARGS)?.let { model ->
            StringBuilder()
                .append("[ ")
                .append(
                    if (model.title.isNullOrBlank()) stringResource(R.string.cs) else model.title
                )
                .append(" ]").append("\n")
                .append("start -> ").append(model.start).append("\n")
                .append("end -> ").append(model.end).also {
                    binding.root.text = it
                }
        }
    }
}