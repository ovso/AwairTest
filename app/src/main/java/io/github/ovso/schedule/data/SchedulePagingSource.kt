package io.github.ovso.schedule.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

class SchedulePagingSource(
    private val service: ScheduleService,
) : PagingSource<String, Event>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, Event> {
        return try {
            val response = service.events(params.key ?: "")
            val data = response.events
            val nextKey = response.nextPageToken
            LoadResult.Page(
                data = data.sortedByDescending {
                    it.start
                },
                prevKey = null,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<String, Event>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
                ?: state.closestPageToPosition(anchorPosition)?.nextKey
        }
    }
}