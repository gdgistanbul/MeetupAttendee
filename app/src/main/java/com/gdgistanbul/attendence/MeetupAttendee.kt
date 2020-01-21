package com.gdgistanbul.attendence

import android.app.Application
import com.facebook.stetho.Stetho
import com.gdgistanbul.di.meetupApiModule
import com.gdgistanbul.di.meetupRepoModule
import com.gdgistanbul.di.meetupViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MeetupAttendee : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // declare used Android context
            androidContext(this@MeetupAttendee)
            // declare modules
            modules(
                listOf(
                    meetupApiModule, meetupRepoModule, meetupViewModelModule
                )
            )
        }

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}