package com.gdgistanbul.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Event(
    @Json(name = "created")
    val created: Long?,
    @Json(name = "duration")
    val duration: Int?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "rsvp_limit")
    val rsvpLimit: Int?,
    @Json(name = "date_in_series_pattern")
    val dateInSeriesPattern: Boolean?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "time")
    val time: Long?,
    @Json(name = "local_date")
    val localDate: String?,
    @Json(name = "local_time")
    val localTime: String?,
    @Json(name = "updated")
    val updated: Long?,
    @Json(name = "utc_offset")
    val utcOffset: Int?,
    @Json(name = "waitlist_count")
    val waitlistCount: Int?,
    @Json(name = "yes_rsvp_count")
    val yesRsvpCount: Int?,
    @Json(name = "venue")
    val venue: Venue?,
    @Json(name = "group")
    val group: Group?,
    @Json(name = "link")
    val link: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "how_to_find_us")
    val howToFindUs: String?,
    @Json(name = "visibility")
    val visibility: String?
)

@JsonClass(generateAdapter = true)
data class Venue(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "lat")
    val lat: Double?,
    @Json(name = "lon")
    val lon: Double?,
    @Json(name = "repinned")
    val repinned: Boolean?,
    @Json(name = "address_1")
    val address1: String?,
    @Json(name = "city")
    val city: String?,
    @Json(name = "country")
    val country: String?,
    @Json(name = "localized_country_name")
    val localizedCountryName: String?
)

@JsonClass(generateAdapter = true)
data class Group(
    @Json(name = "created")
    val created: Long?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "join_mode")
    val joinMode: String?,
    @Json(name = "lat")
    val lat: Double?,
    @Json(name = "lon")
    val lon: Double?,
    @Json(name = "urlname")
    val urlname: String?,
    @Json(name = "who")
    val who: String?,
    @Json(name = "localized_location")
    val localizedLocation: String?,
    @Json(name = "state")
    val state: String?,
    @Json(name = "country")
    val country: String?,
    @Json(name = "region")
    val region: String?,
    @Json(name = "timezone")
    val timezone: String?
)

@JsonClass(generateAdapter = true)
data class Attendance(
    @Json(name = "member")
    val member: Member?,
    @Json(name = "rsvp")
    val rsvp: Rsvp?,
    @Json(name = "attendance_id")
    val attendanceId: Int?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "updated")
    val updated: Long?,
    @Json(name = "guests")
    val guests: Int?
)

@JsonClass(generateAdapter = true)
data class Rsvp(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "response")
    val response: String?,
    @Json(name = "guests")
    val guests: Int?,
    @Json(name = "updated")
    val updated: Long?
)

@JsonClass(generateAdapter = true)
data class Member(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "photo")
    val photo: Photo?,
    @Json(name = "event_context")
    val eventContext: EventContext?
)

@JsonClass(generateAdapter = true)
data class EventContext(
    @Json(name = "host")
    val host: Boolean?
)

@JsonClass(generateAdapter = true)
data class Photo(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "highres_link")
    val highresLink: String?,
    @Json(name = "photo_link")
    val photoLink: String?,
    @Json(name = "thumb_link")
    val thumbLink: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "base_url")
    val baseUrl: String?
)