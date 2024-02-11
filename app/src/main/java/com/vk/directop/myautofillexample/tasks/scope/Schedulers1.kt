package com.vk.directop.myautofillexample.tasks.scope

import com.vk.directop.myautofillexample.tasks.threadsScheduler
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

//Schedulers1
private suspend fun heavyComputation(taskId: Int): Int {
    print("Task $taskId stated")
    delay(1000L)
    print("Task $taskId completed")
    return taskId
}

fun main(): Unit = runBlocking {
    val customDispatcher = 1.threadsScheduler
    val time = System.currentTimeMillis()

    val task1 = async(customDispatcher) {
        heavyComputation(taskId = 1)
    }


    val task2 = async(customDispatcher) {
        heavyComputation(taskId = 2)
    }


    val task3 = async(customDispatcher) {
        heavyComputation(taskId = 3)
    }

    print("Result: ${task1.await() + task2.await() + task3.await()}")

    print("Total time: ${System.currentTimeMillis() - time}")
}