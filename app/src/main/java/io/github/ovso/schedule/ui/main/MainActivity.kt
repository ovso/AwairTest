package io.github.ovso.schedule.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.github.ovso.schedule.databinding.ActivityMainBinding
import io.github.ovso.schedule.ui.main.adapter.ScheduleAdapter
import io.github.ovso.schedule.utils.viewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by viewBinding<ActivityMainBinding>()
    private val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var scheduleAdapter: ScheduleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRv()
        lifecycleScope.launch {
            viewModel.getEvents().collectLatest {
                scheduleAdapter.submitData(it)
            }
        }
    }

    private fun setupRv() {
        with(binding.root) {
            adapter = scheduleAdapter
            addItemDecoration(
                DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL)
            )
        }
    }
}