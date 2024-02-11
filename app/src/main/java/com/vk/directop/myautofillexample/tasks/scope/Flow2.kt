package com.vk.directop.myautofillexample.tasks.scope

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.random.Random

//Flow2
fun numberFlow(): Flow<Int> = flow {
    repeat(5) {
        delay(50)
        emit(Random.nextInt(10))
    }
}

fun main(): Unit = runBlocking {
    withTimeoutOrNull(250) {
        numberFlow().collect {
            delay(30)
            print("$it ")
        }
    }
}