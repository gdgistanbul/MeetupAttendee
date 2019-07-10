package com.gdgistanbul.api

import com.gdgistanbul.model.Attendance
import com.gdgistanbul.model.Event
import retrofit2.http.*

interface MeetupApi {

    @GET("{url_name}/events")
    suspend fun getEvents(@Path("url_name") group: String = "GDGIstanbul"): List<Event>

    @GET("{url_name}/events/{id}/attendance")
    suspend fun getAttendees(
        @Path("group") group: String = "GDGIstanbul",
        @Path("id") id: String,
        @Query("order") order: String = "name"
    ): List<Attendance>


    @POST("{url_name}/events/{id}/attendance")
    suspend fun getAttendenceTaking(@Field("member") id: String, @Field("status") status: String)
}