package com.vk.directop.myautofillexample.tasks.scope

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Exception 6
fun main() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { _, _ -> print("*") }
    val scope = CoroutineScope(exceptionHandler + SupervisorJob())

    val job1 = scope.launch {
        println("A")
        delay(500)
        println("B")
        throw Exception()
    }

    val job2 = scope.launch {
        println("C")
        delay(1000)
        println("#")
    }

    joinAll(job1, job2)
}
//

