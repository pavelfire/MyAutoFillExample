package com.vk.directop.myautofillexample.tasks

import kotlinx.coroutines.newFixedThreadPoolContext

val Int.threadsScheduler
    get() = this.threadsScheduler("${this}Threads")

fun Int.threadsScheduler(name: String) = newFixedThreadPoolContext(
    nThreads = this,
    name = name
)