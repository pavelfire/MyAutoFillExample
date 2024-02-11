package com.vk.directop.myautofillexample.tasks.scope

import android.os.Build
import androidx.annotation.RequiresApi
import com.vk.directop.myautofillexample.tasks.threadsScheduler
import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlin.time.Duration.Companion.seconds

//Race5  рабочий вариант
private val counter = Counter()

private fun increment() {
    counter.count++
}

@RequiresApi(Build.VERSION_CODES.R)
fun main() = runBlocking {
    val time = System.currentTimeMillis()
    val jobs = mutableListOf<Job>()
    val customDispatcher = 2.threadsScheduler

    repeat(1_000) {
        jobs += launch(customDispatcher, block = {
            repeat(1_000) {
                // выбран правильный объект синхронизации будет ровно 1 млн
                synchronized(counter) {
                    increment()
                }
            }
        })
    }

    withTimeout(10.seconds) {
        joinAll(*jobs.toTypedArray())
    }

    print("Final count: ${counter.pretty} in ${System.currentTimeMillis() - time}")
}