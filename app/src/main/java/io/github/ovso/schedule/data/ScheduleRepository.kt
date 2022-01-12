@file:Suppress("unused", "SpellCheckingInspection")

package io.github.ovso.schedule.data

import javax.inject.Inject

interface ScheduleRepository {
    suspend fun events(nextPageToken:String):Events
}

class ScheduleRepositoryImpl @Inject constructor(
    private val service: ScheduleService
) : ScheduleRepository {
    override suspend fun events(nextPageToken:String): Events {
        return service.events(nextPageToken)
    }

}