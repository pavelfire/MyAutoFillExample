package com.vk.directop.myautofillexample.tasks.scope

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Exception 7
fun main() = runBlocking {
    val scope = CoroutineScope(Job())

    val job1 = scope.launch {
        println("A")
        delay(500)
        println("B")
        throw Exception()
    }

    val job2 = scope.launch(Job()) {
        println("C")
        delay(1000)
        println("#")
    }

    joinAll(job1, job2)

    println("D")
}
//

