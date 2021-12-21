package com.opentibiabr.klogin.tools.extensions

import io.ktor.util.sha1

fun String.sha1() = sha1(toByteArray()).toHexString()