package io.github.ovso.schedule.data


import com.google.gson.annotations.SerializedName

data class Events(
  @SerializedName("events")
  val events: List<Event>,
  @SerializedName("next_page_token")
  val nextPageToken: String?
)

data class Event(
  @SerializedName("end")
  val end: String?,
  @SerializedName("start")
  val start: String?,
  @SerializedName("title")
  val title: String?
)