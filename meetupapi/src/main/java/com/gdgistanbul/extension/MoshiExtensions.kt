package com.gdgistanbul.extension

import com.gdgistanbul.model.Login
import com.squareup.moshi.Moshi

fun Moshi.loginAdapter() = adapter(Login::class.java)