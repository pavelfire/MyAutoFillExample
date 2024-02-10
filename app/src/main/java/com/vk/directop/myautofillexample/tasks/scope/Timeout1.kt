package com.vk.directop.myautofillexample.tasks.scope

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

//Timeout 1
fun main(): Unit = runBlocking {
    withTimeout(1300L) {
        repeat(5) { i ->
            print("$i")
            delay(500)
        }
    }
    print("V")
}