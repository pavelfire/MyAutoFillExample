package com.vk.directop.myautofillexample.tasks.scope

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

//Flow1
suspend fun performRequest6(request: Int): String {
    delay(100)
    if (request == 3) throw RuntimeException("*")
    return "$request"
}

fun requestFlow6() = flow {
    for (i in 1..5) {
        emit(i)
    }
}

fun main(): Unit = runBlocking {
    requestFlow6()
        .map { request -> performRequest6(request) }
        //.catch { e -> emit(e.localizedMessage) }
        .collect { response -> print(response) }
}