package com.gdgistanbul.extension

import kotlinx.coroutines.channels.SendChannel

fun <E> SendChannel<E>.safeOffer(value: E): Boolean {
    return runCatching { offer(value) }.getOrDefault(false)
}