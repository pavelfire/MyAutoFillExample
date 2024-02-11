package com.vk.directop.myautofillexample.tasks.scope

import android.os.Build
import androidx.annotation.RequiresApi
import com.vk.directop.myautofillexample.tasks.threadsScheduler
import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withTimeout
import kotlin.time.Duration.Companion.seconds

//Race8 рабочий вариант
private val counter = Counter()
private val mutex = Mutex()

private suspend fun increment() = mutex.withLock {
    counter.count++
}

@RequiresApi(Build.VERSION_CODES.R)
fun main() = runBlocking {
    val time = System.currentTimeMillis()
    val jobs = mutableListOf<Job>()
    val customDispatcher = 2.threadsScheduler

    repeat(1_000) {
        jobs += launch(customDispatcher) {
            repeat(1_000) {
                increment()
            }
        }
    }

    withTimeout(10.seconds) {
        joinAll(*jobs.toTypedArray())
    }

    print("Final count: ${counter.pretty} in ${System.currentTimeMillis() - time}")
}