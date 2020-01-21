package com.gdgistanbul.extension

import java.text.SimpleDateFormat

fun String.formatDateTime(currentPattern: String, newPattern: String): String? {
    return runCatching {
        val currentDate = SimpleDateFormat(currentPattern).parse(this)
        return SimpleDateFormat(newPattern).format(currentDate)
    }.getOrNull()
}