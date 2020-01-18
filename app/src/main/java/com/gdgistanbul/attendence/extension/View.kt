package com.gdgistanbul.attendence.extension

import android.view.View
import com.gdgistanbul.extension.android.checkMainThread
import com.gdgistanbul.extension.safeOffer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate

inline fun View.onClick(crossinline block: () -> Unit) = setOnClickListener { block() }

inline fun View.onLongClick(
    consume: Boolean = true,
    crossinline block: () -> Unit
) = setOnLongClickListener { block(); consume }

@ExperimentalCoroutinesApi
fun View.clicks(): Flow<Unit> = callbackFlow<Unit> {
    checkMainThread()
    setOnClickListener { safeOffer(Unit) }
    awaitClose { setOnClickListener(null) }
}.conflate()