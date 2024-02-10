package com.vk.directop.myautofillexample.tasks.scope

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Cancellation 1
fun main() = runBlocking {
    val job = launch {
        try {
            repeat(5) { i ->
                println("A$i")
                delay(100)
            }
        } finally {
            println("B")
        }
    }
    delay(250)
    println("C")
    job.cancelAndJoin()
    println("D")
}

//A0
//A1
//A2
//C
//B
//D
