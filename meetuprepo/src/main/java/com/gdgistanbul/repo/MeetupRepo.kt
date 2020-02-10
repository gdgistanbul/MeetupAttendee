package com.gdgistanbul.repo

import android.content.SharedPreferences
import com.gdgistanbul.api.MeetupApi
import com.gdgistanbul.api.MeetupSecureApi
import com.gdgistanbul.api.Secrets
import com.gdgistanbul.extension.loginAdapter
import com.gdgistanbul.model.Event
import com.gdgistanbul.model.Member
import com.squareup.moshi.Moshi

class MeetupRepo(
    private val meetupApi: MeetupApi,
    private val meetupSecureApi: MeetupSecureApi,
    private val sharedPreferences: SharedPreferences,
    private val moshi: Moshi
) {
    suspend fun getEvents(): List<Event> = meetupApi.getEvents()

    suspend fun getPastEvents(): List<Event> = meetupApi.getEvents(status = "past")

    suspend fun authenticate(code: String) {
        val login = meetupSecureApi.getAccessToken(
            Secrets.MEETUP_KEY,
            Secrets.MEETUP_SECRET,
            code,
            Secrets.MEETUP_REDIRECT_URL,
            "authorization_code"
        )
        sharedPreferences.edit().putString("login", moshi.loginAdapter().toJson(login)).apply()
    }

    suspend fun getSelf(): Member = meetupApi.getMember()

    suspend fun getMember(id: Int): Member = meetupApi.getMember(id.toString())
}