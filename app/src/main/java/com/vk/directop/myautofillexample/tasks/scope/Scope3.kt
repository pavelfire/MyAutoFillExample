package com.vk.directop.myautofillexample.tasks.scope

import com.vk.directop.myautofillexample.tasks.threadsScheduler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

//Scope3 A1 B2 C1
fun main(): Unit = runBlocking {
    val context1 = 1.threadsScheduler("1")
    val context2 = 1.threadsScheduler("2")

    launch(context1) {
        println("A$ threadName")

        withContext(context2) {
            delay(500)
            println("B$ threadName")
        }

        println("C$ threadName")
    }
}