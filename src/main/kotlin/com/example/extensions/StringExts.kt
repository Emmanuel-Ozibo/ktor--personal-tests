package com.example.extensions

fun String?.isNotNull(): Boolean = this != null
fun String?.orEmpty(): String = this ?: ""
