package com.vk.directop.myautofillexample.tasks.scope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

//Flow3
private fun stringFlow(): Flow<String> = flow {
    ('A'..'E').forEach { char ->
        emit("$char ->")
        delay(1000)
    }
}

fun main(): Unit = runBlocking {
    val time = System.currentTimeMillis()
    var result = ""

    stringFlow().flowOn(Dispatchers.IO).map { item ->
        delay(500)
        item
    }.flowOn(Dispatchers.Default).collect { item ->
        result += item
    }

    print("Result: $result   ${System.currentTimeMillis() - time}")
}