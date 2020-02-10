package com.gdgistanbul.api.authenticator

import android.content.SharedPreferences
import com.gdgistanbul.extension.loginAdapter
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor(
    private val sharedPreferences: SharedPreferences,
    private val moshi: Moshi
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = sharedPreferences.getString("login", null)?.let { json ->
            moshi.loginAdapter().fromJson(json)
        }?.accessToken ?: return chain.proceed(chain.request())

        val newRequest = chain.request().newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()

        return chain.proceed(newRequest)
    }
}