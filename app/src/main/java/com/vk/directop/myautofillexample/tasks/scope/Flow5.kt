package com.vk.directop.myautofillexample.tasks.scope

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

//Flow5
private fun stringFlow(): Flow<String> = flow {
    ('A'..'E').forEach { char ->
        emit("$char ->")
        delay(50)
    }
}

fun Flow<String>.timed(delay: Duration): Flow<String> = flow {
    var time = System.currentTimeMillis()
    buffer(1, BufferOverflow.DROP_OLDEST).collect { item ->
        if (time == System.currentTimeMillis()) {
            time = System.currentTimeMillis()
        }
        emit("${System.currentTimeMillis() - time}  $item")
        delay(delay)
    }
}

fun main(): Unit = runBlocking {
    val sharedFlow = stringFlow().shareIn(this, SharingStarted.Lazily, 0)
    launch {
        sharedFlow.collect {
            print(it)
        }
    }
    launch {
        sharedFlow
            .timed(1000.milliseconds)
            .collect {
                print(it)
            }
    }
}