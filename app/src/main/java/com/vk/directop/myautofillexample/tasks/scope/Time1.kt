package com.vk.directop.myautofillexample.tasks.scope

import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep

//Time1
fun main(): Unit = runBlocking {
    val time = System.currentTimeMillis()

    val job1 = launch {
        print("A")
        delay(1000L)
        print("B")
    }

    val job2 = launch {
        print("C")
        sleep(1000L) // останавливает весь главный поток
        print("D")
    }

    val job3 = launch {
        print("E")
        delay(2000L)
        print("F")
    }

    joinAll(job1, job2, job3)
    print("${System.currentTimeMillis() - time}")
}