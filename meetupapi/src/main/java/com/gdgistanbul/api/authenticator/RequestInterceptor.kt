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
        val login = sharedPreferences.getString("login", null)?.let { json ->
            moshi.loginAdapter().fromJson(json)
        } ?: return chain.proceed(chain.request())

        val originalRequest = chain.request()

        val headers = originalRequest.headers().newBuilder()
            .add("Authorization", "Bearer ${login.accessToken}")
            .build()

        val request = originalRequest.newBuilder()
            .headers(headers)
            .build()

        return chain.proceed(request)
    }
}