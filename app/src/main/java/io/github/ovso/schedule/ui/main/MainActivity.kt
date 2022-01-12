package io.github.ovso.schedule.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.github.ovso.schedule.databinding.ActivityMainBinding
import io.github.ovso.schedule.ui.main.adapter.ScheduleAdapter
import io.github.ovso.schedule.utils.viewBinding
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
        viewModel.items.observe(this) {
            scheduleAdapter.submitList(it)
        }
        viewModel.fetchEvents()
    }

    private fun setupRv() {
        with(binding.root) {
            adapter = scheduleAdapter
            addItemDecoration(
                DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL)
            )
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val lastVisibleItemPosition =
                        (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                    val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                    // 스크롤이 끝에 도달했는지 확인
                    if (canScrollVertically(5).not() && lastVisibleItemPosition == itemTotalCount) {
                        viewModel.fetchEvents()
                    }
                }
            })
        }
    }
}