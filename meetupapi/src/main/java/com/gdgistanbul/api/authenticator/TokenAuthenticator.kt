package com.gdgistanbul.api.authenticator

import android.content.SharedPreferences
import com.gdgistanbul.api.MeetupSecureApi
import com.gdgistanbul.api.Secrets
import com.gdgistanbul.extension.loginAdapter
import com.squareup.moshi.Moshi
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val secureApi: MeetupSecureApi,
    private val sharedPreferences: SharedPreferences,
    private val moshi: Moshi
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val login = sharedPreferences.getString("login", null)?.let { json ->
            moshi.loginAdapter().fromJson(json)?.refreshToken?.let { refreshToken ->
                secureApi.getAccessToken(
                    Secrets.MEETUP_KEY,
                    Secrets.MEETUP_SECRET,
                    refreshToken
                )
            }
        } ?: return null
        sharedPreferences.edit()
            .putString("login", moshi.loginAdapter().toJson(login))
            .apply()
        return response.request().newBuilder()
            .header("Authorization", "Bearer ${login.accessToken}")
            .build()
    }

}