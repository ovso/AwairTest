package io.github.ovso.schedule.ui.info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.ovso.schedule.databinding.ActivityInfoBinding
import io.github.ovso.schedule.utils.viewBinding
import java.lang.StringBuilder

class InfoActivity : AppCompatActivity() {
    private val binding by viewBinding<ActivityInfoBinding>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getParcelableExtra<InfoModel>("args")?.let { model ->
            StringBuilder()
                .append("[ ").append(model.title).append(" ]").append("\n")
                .append("start -> ").append(model.start).append("\n")
                .append("end -> ").append(model.end).also {
                    binding.root.text = it
                }
        }
    }
}