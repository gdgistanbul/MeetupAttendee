package com.gdgistanbul.api

import com.gdgistanbul.model.Login
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MeetupSecureApi {

    @FormUrlEncoded
    @POST("oauth2/access")
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String = "authorization_code",
        @Field("redirect_uri") redirectUri: String,
        @Field("code") code: String
    ): Login
}