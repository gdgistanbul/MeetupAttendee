package com.gdgistanbul.attendence.extension

val Any.classTag: String get() = this.javaClass.simpleName
val Any.methodTag get() = classTag + object : Any() {}.javaClass.enclosingMethod?.name