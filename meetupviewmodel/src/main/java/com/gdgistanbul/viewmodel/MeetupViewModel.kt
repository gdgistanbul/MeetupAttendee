package com.gdgistanbul.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgistanbul.model.Event
import com.gdgistanbul.repo.MeetupRepo
import kotlinx.coroutines.launch

class MeetupViewModel(private val meetupRepo: MeetupRepo) : ViewModel() {

    private val _eventsLiveData = MutableLiveData<List<Event>>()
    private val _toastLiveData = MutableLiveData<String>()
    private val _loginLiveData = MutableLiveData<Unit>()
    val eventsLiveData: LiveData<List<Event>> = _eventsLiveData
    val toastLiveData: LiveData<String> = _toastLiveData
    val loginLiveData: LiveData<Unit> = _loginLiveData

    fun refreshEvents() = viewModelScope.launch {
        try {
            _eventsLiveData.postValue(meetupRepo.getEvents())
        } catch (e: Exception) {
            _toastLiveData.postValue(e.message)
            Log.d("meetupviewmodel", e.message, e)
        }
    }

    fun authenticate(code: String) = viewModelScope.launch {
        try {
            meetupRepo.authenticate(code)
            _loginLiveData.postValue(Unit)
        } catch (e: Exception) {
            _toastLiveData.postValue(e.message)
            Log.d("meetupviewmodel", e.message, e)
        }
    }
}

