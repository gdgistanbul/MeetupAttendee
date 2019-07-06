package com.gdgistanbul.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventContext(val host: Boolean)

@JsonClass(generateAdapter = true)
data class Photo(
    @Json(name ="thumb_link") val thumbnail: String,
    @Json(name ="photo_link") val photo: String
)

@JsonClass(generateAdapter = true)
data class Member(
    @Json(name ="event_context") val eventContext: EventContext,
    val id: String,
    val name: String,
    val photo: Photo,
    val role: String,
    val title: String
)

@JsonClass(generateAdapter = true)
data class RSVP(
    val response: String,
    val guests: Int
)

@JsonClass(generateAdapter = true)
data class Attendence(val member: Member, val rsvp: RSVP, val status: String, val updated: String)

@JsonClass(generateAdapter = true)
data class Event(
    @Json(name ="attendance_count") val attendenceCount: Int,
    val description: String,
    val id: String,
    val link: String,
    val name: String
)