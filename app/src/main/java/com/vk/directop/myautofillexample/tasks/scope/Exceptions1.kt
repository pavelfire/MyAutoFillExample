package com.vk.directop.myautofillexample.tasks.scope

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Exception 1
fun main() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { _, _ -> print("*") }

    val job = GlobalScope.launch(exceptionHandler) {
        val child1 = launch {
            println("A")
            delay(500)
            println("B")
            throw RuntimeException()
        }

        val child2 = launch {
            println("C")
            delay(1000)
            println("#")
        }

        joinAll(child1, child2)
    }
    job.join()
}
//A
//C
//B
//*

