package com.gdgistanbul.attendence.extension

import androidx.annotation.StringRes
import com.google.android.material.tabs.TabLayout

fun TabLayout.addTab(title: String) = addTab(newTab().setText(title))
fun TabLayout.addTab(@StringRes resId: Int) = addTab(newTab().setText(resId))

inline fun TabLayout.onTabSelected(crossinline block: (tab: TabLayout.Tab) -> Unit) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab) {}
        override fun onTabUnselected(tab: TabLayout.Tab) {}
        override fun onTabSelected(tab: TabLayout.Tab) {
            block(tab)
        }
    })
}