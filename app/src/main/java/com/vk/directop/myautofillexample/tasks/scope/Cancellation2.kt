package com.vk.directop.myautofillexample.tasks.scope

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Cancellation 2
fun main() = runBlocking {
    val supervisor = SupervisorJob()

    with(CoroutineScope(coroutineContext + supervisor)) {
        val child = launch {
            try {
                print("A")
                delay(Long.MAX_VALUE)
            } finally {
                print("B")
            }
        }

        delay(1000)
        print("C")
        supervisor.cancel()
    }

    println("D")
}

//ACD
//B

