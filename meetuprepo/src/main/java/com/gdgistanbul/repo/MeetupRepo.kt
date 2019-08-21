package com.gdgistanbul.repo

import com.gdgistanbul.api.MeetupApi
import com.gdgistanbul.api.MeetupSecureApi
import com.gdgistanbul.model.Event
import com.gdgistanbul.model.Login
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MeetupRepo(val meetupApi: MeetupApi, val meetupSecureApi: MeetupSecureApi) {
    suspend fun getEvents(): List<Event> = withContext(Dispatchers.IO) {
        meetupApi.getEvents()
    }

    suspend fun getAccessToken(code: String): Login = withContext(Dispatchers.IO) {
        meetupSecureApi.getAccessToken(code = code)
    }
}