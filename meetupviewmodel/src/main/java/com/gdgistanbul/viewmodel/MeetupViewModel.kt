package com.gdgistanbul.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgistanbul.Event
import com.gdgistanbul.attendence.extension.classTag
import com.gdgistanbul.repo.MeetupRepo
import kotlinx.coroutines.launch
import com.gdgistanbul.model.Event as EventModel

class MeetupViewModel(private val meetupRepo: MeetupRepo) : ViewModel() {

    private val _eventsLiveData = MutableLiveData<List<EventModel>>()
    private val _toastLiveData = MutableLiveData<Event<String>>()
    private val _loginLiveData = MutableLiveData<Event<Unit>>()
    private val _eventLiveData = MutableLiveData<EventModel>()
    val eventsLiveData: LiveData<List<EventModel>> = _eventsLiveData
    val toastLiveData: LiveData<Event<String>> = _toastLiveData
    val loginLiveData: LiveData<Event<Unit>> = _loginLiveData
    val eventLiveData: LiveData<EventModel> = _eventLiveData

    fun refreshEvents() = viewModelScope.launch {
        try {
            _eventsLiveData.postValue(meetupRepo.getEvents())
        } catch (e: Exception) {
            _toastLiveData.postValue(Event(e.message.toString()))
            Log.d(classTag, e.message, e)
        }
    }

    fun authenticate(code: String) = viewModelScope.launch {
        try {
            meetupRepo.authenticate(code)
            _loginLiveData.postValue(Event(Unit))
        } catch (e: Exception) {
            _toastLiveData.postValue(Event(e.message.toString()))
            Log.d("meetupviewmodel", e.message, e)
        }
    }

    fun getEvent(id: String) = viewModelScope.launch {
        try {
            _eventLiveData.postValue(meetupRepo.getEvent(id))
        } catch (e: Exception) {
            _toastLiveData.postValue(Event(e.message.toString()))
            Log.d("meetupviewmodel", e.message, e)
        }
    }
}

