package com.gdgistanbul.di

import com.gdgistanbul.api.MeetupApi
import com.gdgistanbul.api.MeetupSecureApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL_API = "https://api.meetup.com/"
const val BASE_URL_SECURE = "https://secure.meetup.com/"

val meetupApiModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_API)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        retrofit.create(MeetupApi::class.java)
    }
}

val meetupSecureModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_SECURE)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        retrofit.create(MeetupSecureApi::class.java)
    }
}