package com.gdgistanbul.di

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.gdgistanbul.api.MeetupApi
import com.gdgistanbul.api.MeetupSecureApi
import com.gdgistanbul.api.authenticator.TokenAuthenticator
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL_API = "https://api.meetup.com/"
const val BASE_URL_SECURE = "https://secure.meetup.com/"

val meetupApiModule = module {

    single {
        OkHttpClient.Builder()
            .authenticator(get())
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_API)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        retrofit.create(MeetupApi::class.java)
    }

    single {
        TokenAuthenticator(get(), get(), get())
    }

    single {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        val create: SharedPreferences = EncryptedSharedPreferences.create(
            "secret_shared_prefs",
            masterKeyAlias,
            androidContext(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        create
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_SECURE)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        retrofit.create(MeetupSecureApi::class.java)
    }

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

}