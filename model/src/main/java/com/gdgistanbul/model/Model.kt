package com.gdgistanbul.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Event(
    val created: Long?,
    val duration: Int?,
    val id: String?,
    val name: String?,
    val status: String?,
    val time: Long?,
    val venue: Venue?,
    val group: Group?,
    val link: String?,
    val updated: Long?,
    val visibility: String?,
    val description: String?,
    @Json(name = "local_date")
    val localDate: String?,
    @Json(name = "local_time")
    val localTime: String?,
    @Json(name = "rsvp_limit")
    val rsvpLimit: Int?,
    @Json(name = "how_to_find_us")
    val howToFindUs: String?,
    @Json(name = "date_in_series_pattern")
    val dateInSeriesPattern: Boolean?,
    @Json(name = "utc_offset")
    val utcOffset: Int?,
    @Json(name = "waitlist_count")
    val waitlistCount: Int?,
    @Json(name = "yes_rsvp_count")
    val yesRsvpCount: Int?,
    @Json(name = "featured_photo")
    val featuredPhoto: Photo?
) {
    val localDateTime: String?
        get() = takeIf { localDate != null && localTime != null }
            ?.let { "$localDate $localTime" }

    companion object {
        const val LOCAL_DATE_TIME_PATTERN = "YYYY-MM-DD hh:mm"
    }
}

@JsonClass(generateAdapter = true)
data class Venue(
    val id: Int?,
    val name: String?,
    val lat: Double?,
    val lon: Double?,
    val repinned: Boolean?,
    val city: String?,
    val country: String?,
    @Json(name = "address_1")
    val address1: String?,
    @Json(name = "localized_country_name")
    val localizedCountryName: String?
)

@JsonClass(generateAdapter = true)
data class Group(
    val created: Long?,
    val name: String?,
    val id: Int?,
    val joinMode: String?,
    val lat: Double?,
    val lon: Double?,
    val urlname: String?,
    val who: String?,
    val state: String?,
    val country: String?,
    val region: String?,
    val timezone: String?,
    @Json(name = "localized_location")
    val localizedLocation: String?
)

@JsonClass(generateAdapter = true)
data class Attendance(
    val member: Member?,
    val rsvp: Rsvp?,
    val status: String?,
    val updated: Long?,
    val guests: Int?,
    @Json(name = "attendance_id")
    val attendanceId: Int?
)

@JsonClass(generateAdapter = true)
data class Rsvp(
    val id: Int?,
    val response: String?,
    val guests: Int?,
    val updated: Long?
)

@JsonClass(generateAdapter = true)
data class Member(
    val id: Int?,
    val name: String?,
    val email: String?,
    val status: String?,
    val joined: Long?,
    val city: String?,
    val country: String?,
    @Json(name = "localized_country_name")
    val localizedCountryName: String?,
    val lat: Double?,
    val lon: Double?,
    val photo: Photo?,
    @Json(name = "event_context")
    val eventContext: EventContext?,
    @Json(name = "is_pro_admin")
    val isProAdmin: Boolean?
) {
    val firstName get() = name?.removeSuffix(lastName.toString())
    val lastName get() = name?.split("\\s+")?.takeLast(1)?.get(0)?.takeIf { it.isNotBlank() }
}

@JsonClass(generateAdapter = true)
data class EventContext(
    val host: Boolean?
)

@JsonClass(generateAdapter = true)
data class Photo(
    val id: Int?,
    @Json(name = "highres_link")
    val highresLink: String?,
    @Json(name = "photo_link")
    val photoLink: String?,
    @Json(name = "thumb_link")
    val thumbLink: String?,
    val type: String?,
    @Json(name = "base_url")
    val baseUrl: String?
)

@JsonClass(generateAdapter = true)
data class Login(
    @Json(name = "access_token")
    val accessToken: String?,
    @Json(name = "refresh_token")
    val refreshToken: String?,
    @Json(name = "token_type")
    val tokenType: String?,
    @Json(name = "expires_in")
    val expiresIn: Int?
)