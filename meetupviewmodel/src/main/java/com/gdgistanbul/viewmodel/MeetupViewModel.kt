package com.gdgistanbul.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgistanbul.model.Event
import com.gdgistanbul.repo.MeetupRepo
import kotlinx.coroutines.launch

class MeetupViewModel(private val meetupRepo: MeetupRepo) : ViewModel() {


    val eventsLiveData = MutableLiveData<List<Event>>()
    val toastLiveData = MutableLiveData<String>()

    fun refreshEvents() = viewModelScope.launch {
        try {
            eventsLiveData.postValue(meetupRepo.getEvents())
        } catch (e: Exception) {
            toastLiveData.postValue(e.message)
            Log.d("meetupviewmodel", e.message, e)
        }
    }


}

