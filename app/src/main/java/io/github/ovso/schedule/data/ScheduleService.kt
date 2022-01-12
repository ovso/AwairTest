@file:Suppress("SpellCheckingInspection")

package io.github.ovso.schedule.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleService {
    @GET("/events")
    suspend fun events(@Query("next_page_token") nextPageToken: String = ""): Events
}