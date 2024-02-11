package com.vk.directop.myautofillexample.tasks.scope

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Time3
fun main() {
    val time = System.currentTimeMillis()

    runBlocking {
        launch {
            repeat(10) {
                delay(100)
                print("$it: ${System.currentTimeMillis() - time}, ")
            }
        }
    }

    println("\nTotal: ${System.currentTimeMillis() - time}")
}