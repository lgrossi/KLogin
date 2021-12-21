package com.opentibiabr.klogin.tools.extensions

fun ByteArray.toHexString() = joinToString("") { "%02x".format(it) }