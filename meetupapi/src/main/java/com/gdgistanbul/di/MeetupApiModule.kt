package com.gdgistanbul.di

import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.gdgistanbul.api.MeetupApi
import com.gdgistanbul.api.MeetupSecureApi
import com.gdgistanbul.api.authenticator.RequestInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

const val BASE_URL_API = "https://api.meetup.com/"
const val BASE_URL_SECURE = "https://secure.meetup.com/"

val meetupApiModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<RequestInterceptor>())
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_API)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        retrofit.create<MeetupApi>()
    }

    single {
        RequestInterceptor(get(), get())
    }

    single {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        EncryptedSharedPreferences.create(
            "secret_shared_prefs",
            masterKeyAlias,
            androidContext(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_SECURE)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        retrofit.create<MeetupSecureApi>()
    }

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

}