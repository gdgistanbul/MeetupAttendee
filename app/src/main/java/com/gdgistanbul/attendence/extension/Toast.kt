package com.gdgistanbul.attendence.extension

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

fun View.toast(text: CharSequence) = Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
fun Activity.toast(text: CharSequence) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
fun Fragment.toast(text: CharSequence) =
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()

fun View.longToast(text: CharSequence) = Toast.makeText(context, text, Toast.LENGTH_LONG).show()
fun Activity.longToast(text: CharSequence) = Toast.makeText(this, text, Toast.LENGTH_LONG).show()
fun Fragment.longToast(text: CharSequence) =
    Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()

