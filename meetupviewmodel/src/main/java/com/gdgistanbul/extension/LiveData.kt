package com.gdgistanbul.attendence.extension

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.gdgistanbul.Event
import com.gdgistanbul.EventObserver

inline fun <T> Activity.observe(
    liveData: LiveData<T>,
    crossinline onChanged: (T) -> Unit
) = Observer<T> { onChanged(it) }.also { liveData.observe(this as LifecycleOwner, it) }

inline fun <T> Activity.observeEvent(
    liveData: LiveData<Event<T>>,
    crossinline onChanged: (T) -> Unit
) = EventObserver<T> { onChanged(it) }.also { liveData.observe(this as LifecycleOwner, it) }

inline fun <T> Fragment.observe(
    liveData: LiveData<T>,
    crossinline onChanged: (T) -> Unit
) = Observer<T> { onChanged(it) }.also { liveData.observe(viewLifecycleOwner, it) }

inline fun <T> Fragment.observeEvent(
    liveData: LiveData<Event<T>>,
    crossinline onChanged: (T) -> Unit
) = EventObserver<T> { onChanged(it) }.also { liveData.observe(viewLifecycleOwner, it) }