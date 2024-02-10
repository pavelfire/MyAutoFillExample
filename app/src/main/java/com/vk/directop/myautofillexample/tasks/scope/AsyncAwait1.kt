package com.vk.directop.myautofillexample.tasks.scope

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime.now


suspend fun performTask(id: Int): Int {
    delay(1000)
    return id * 2
}

//AsyncAwait 1
fun main(): Unit = runBlocking {
    val time = now().nano
    val deferred1 = async { performTask(1) }
    val deferred2 = async { performTask(2) }

    print("A")
    val result1 = deferred1.await()
    val result2 = deferred2.await()

    print("$result1 $result2 ${(time.passed())}")
}

fun Int.passed() = now().nano - this

