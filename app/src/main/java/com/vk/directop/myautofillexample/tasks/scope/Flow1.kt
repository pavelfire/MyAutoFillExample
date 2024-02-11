package com.vk.directop.myautofillexample.tasks.scope

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

//Flow1
suspend fun performRequest(request: Int): String {
    delay(100)
    if (request == 3) throw RuntimeException("* exception catch $request")
    return "$request"
}

fun requestFlow() = flow {
    for (i in 1..5) {
        emit(i)
    }
}

fun main(): Unit = runBlocking {
    requestFlow()
        .map { request -> performRequest(request) }
        .catch { e -> emit(e.localizedMessage) }
        .collect { response -> print(response) }
    // добавление catch в месте вызова спасёт приложение от падения
}