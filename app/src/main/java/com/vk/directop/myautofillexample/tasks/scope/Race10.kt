package com.vk.directop.myautofillexample.tasks.scope

import android.os.Build
import androidx.annotation.RequiresApi
import com.vk.directop.myautofillexample.tasks.threadsScheduler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withTimeout
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

//Race10
private val counter = Counter()
private val mutex = Mutex()

private suspend fun increment() {
    delay(Random.nextLong(0, 2))
    counter.count++
}

@RequiresApi(Build.VERSION_CODES.R)
fun main() = runBlocking {
    val time = System.currentTimeMillis()
    val jobs = mutableListOf<Job>()
    val customDispatcher = 2.threadsScheduler

    repeat(1_000) {
//        mutex.lock()  //  по моему если раскомментировать будет дедлок иначе timeout exception
        jobs += launch(customDispatcher) {
            repeat(1_000) {
                mutex.withLock {
                    increment()
                }
            }
        }
//        mutex.unlock()
    }

    withTimeout(10.seconds) {
        joinAll(*jobs.toTypedArray())
    }

    print("Final count: ${counter.pretty} in ${System.currentTimeMillis() - time}")
}