package com.vk.directop.myautofillexample.tasks.scope

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep

//Time2
fun main() {
    val time = System.currentTimeMillis()

    runBlocking {
        repeat(10) {
            launch {
                sleep(100)
                print("$it: ${System.currentTimeMillis() - time}, ")
            }
        }
    }

    println("\nTotal: ${System.currentTimeMillis() - time}")
}