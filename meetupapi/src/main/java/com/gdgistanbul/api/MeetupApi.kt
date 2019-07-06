package com.gdgistanbul.api

import com.gdgistanbul.model.Event
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MeetupApi {

    @GET("events")
    suspend fun getEvents(@Query("has_ended") hasEnded: Boolean = true): List<Event>

    @GET("events/{id}/attendance")
    suspend fun getAttendees(@Path("id") id: String, @Query("order") order: String = "name")


    @GET("events/{id}/attendance")
    suspend fun getAttendenceTaking(@Field("member") id: String, @Field("status") status: String )
}