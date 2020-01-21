package com.gdgistanbul.attendence.extension

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

val Context.layoutInflater get() = LayoutInflater.from(this)
val View.layoutInflater: LayoutInflater get() = LayoutInflater.from(context)

fun <V : View> LayoutInflater.inflate(@LayoutRes layoutResId: Int): V {
    @Suppress("UNCHECKED_CAST") return inflate(layoutResId, null, false) as V
}

fun <V : View> Context.inflate(@LayoutRes layoutResId: Int): V {
    return LayoutInflater.from(this).inflate(layoutResId)
}

fun <V : View> ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean): V {
    @Suppress("UNCHECKED_CAST") return layoutInflater.inflate(layoutRes, this, attachToRoot) as V
}

fun ViewGroup.inflateAndAttach(@LayoutRes layoutRes: Int) {
    layoutInflater.inflate(layoutRes, this, true)
}