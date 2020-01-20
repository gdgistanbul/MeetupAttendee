package com.gdgistanbul.attendence.extension

import androidx.annotation.StringRes
import com.google.android.material.tabs.TabLayout

fun TabLayout.addTab(title: String) = addTab(newTab().setText(title))
fun TabLayout.addTab(@StringRes resId: Int) = addTab(newTab().setText(resId))