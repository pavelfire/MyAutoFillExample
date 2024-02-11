package com.vk.directop.myautofillexample.tasks.scope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

//Flow4
private fun stringFlow(): Flow<String> = flow {
    ('A'..'E').forEach { char ->
        emit("$char ->")
        delay(50)
    }
}

@OptIn(FlowPreview::class)
fun main(): Unit = runBlocking {
    val time = System.currentTimeMillis()
    var result = ""

    stringFlow().flatMapMerge { value ->
        flow {
            // если раскомментировать упадёт с ошибкой
            // не переключайте контекст внутри операторов^ а исспользуйте flowOn
            //withContext(Dispatchers.IO) {
                delay(100)
                emit(value)
            //}
        }
    }.collect { item ->
        result += item
    }

    print("Result: $result   ${System.currentTimeMillis() - time}")
}