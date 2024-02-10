package com.vk.directop.myautofillexample.tasks.scope

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Scope1 cab
fun main(): Unit = runBlocking {
    val jobA = launch {
        delay(500)
        println("A")
    }

    val jobB = launch {
        delay(1000)
        println("B")
    }

    println("C")
}