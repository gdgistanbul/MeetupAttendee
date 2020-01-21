package com.gdgistanbul.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgistanbul.Event
import com.gdgistanbul.attendence.extension.classTag
import com.gdgistanbul.model.EventStatus
import com.gdgistanbul.model.Member
import com.gdgistanbul.repo.MeetupRepo
import kotlinx.coroutines.*
import com.gdgistanbul.model.Event as EventModel

class EventListViewModel(private val meetupRepo: MeetupRepo) : ViewModel() {
    private val _memberLiveData = MutableLiveData<Member>()
    private val _eventsLiveData = MutableLiveData<List<EventModel>>()
    private val _toastLiveData = MutableLiveData<Event<String>>()
    val memberLiveData: LiveData<Member> = _memberLiveData
    val eventsLiveData: LiveData<List<EventModel>> = _eventsLiveData
    val toastLiveData: LiveData<Event<String>> = _toastLiveData

    // TODO: It could be better to keep them in a local persistence system
    private var upcomingEvents = listOf<EventModel>()
    private var pastEvents = listOf<EventModel>()

    var eventStatus = EventStatus.UPCOMING
        set(value) {
            field = value
            updateEvents()
        }

    var query: String? = null
        set(value) {
            field = value
            updateEvents()
        }

    init {
        viewModelScope.launch {
            try {
                coroutineScope {
                    listOf(
                        async { _memberLiveData.value = meetupRepo.getSelf() },
                        async { upcomingEvents = meetupRepo.getEvents() },
                        async { pastEvents = meetupRepo.getPastEvents() }
                    ).awaitAll()
                }
                updateEvents()
            } catch (e: Exception) {
                _toastLiveData.value = Event(e.message.toString())
                Log.d(classTag, e.message, e)
            }
        }
    }

    private fun updateEvents() = viewModelScope.launch {
        val eventList = withContext(Dispatchers.Default) {
            when (eventStatus) {
                EventStatus.UPCOMING -> upcomingEvents
                EventStatus.PAST -> pastEvents
            }.let {
                if (query.isNullOrBlank()) it
                else it.filter { it.name?.contains(query!!, true) == true }
            }
        }

        _eventsLiveData.value = eventList
    }
}