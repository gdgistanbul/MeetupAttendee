package com.gdgistanbul.repo

import com.gdgistanbul.api.MeetupApi
import com.gdgistanbul.model.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MeetupRepo(val meetupApi: MeetupApi) {
    suspend fun getEvents(): List<Event> = withContext(Dispatchers.IO) {
        meetupApi.getEvents()
    }

}