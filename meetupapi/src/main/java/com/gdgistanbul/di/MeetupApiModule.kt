package com.gdgistanbul.di

import com.gdgistanbul.api.MeetupApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val meetupApiModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.meetup.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        retrofit.create(MeetupApi::class.java)
    }
}