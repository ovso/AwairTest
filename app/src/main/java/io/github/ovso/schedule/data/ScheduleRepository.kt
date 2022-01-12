@file:Suppress("unused", "SpellCheckingInspection")

package io.github.ovso.schedule.data

import javax.inject.Inject

interface ScheduleRepository {
    suspend fun events():Events
}

class ScheduleRepositoryImpl @Inject constructor(
    private val service: ScheduleService
) : ScheduleRepository {
    override suspend fun events(): Events {
        return service.events()
    }

}