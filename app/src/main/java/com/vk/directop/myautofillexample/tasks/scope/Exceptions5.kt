package com.vk.directop.myautofillexample.tasks.scope

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

//Exception 5
fun main() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { _, _ -> print("*") }
    val scope = CoroutineScope(Job())

    val job = scope.launch(exceptionHandler) {
        supervisorScope {
            val child1 = launch {
                println("A")
                delay(500)
                println("B")
                throw Exception()
            }

            val child2 = launch {
                println("C")
                delay(1000)
                println("#")
            }

            joinAll(child1, child2)
        }
    }
    job.join()
}
//

