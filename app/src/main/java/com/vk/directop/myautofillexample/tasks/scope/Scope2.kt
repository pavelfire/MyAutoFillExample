package com.vk.directop.myautofillexample.tasks.scope

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Scope2 abc
fun main(): Unit = runBlocking {
    val jobA = launch {
        delay(500)
        println("A")
    }

    coroutineScope {
        val jobB = launch {
            delay(1000)
            println("B")
        }
    }

    println("C")
}