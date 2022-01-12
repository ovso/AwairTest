@file:Suppress("unused", "SpellCheckingInspection")

package io.github.ovso.schedule.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ScheduleRepository {
    fun events(): Flow<PagingData<Event>>
}

class ScheduleRepositoryImpl @Inject constructor(
    private val service: ScheduleService
) : ScheduleRepository {
    override fun events(): Flow<PagingData<Event>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                SchedulePagingSource(service)
            }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}