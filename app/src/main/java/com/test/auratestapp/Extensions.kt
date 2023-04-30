package com.test.auratestapp

import java.util.concurrent.atomic.AtomicInteger

val id = AtomicInteger(0)
fun getId(): Int {
    return id.incrementAndGet()
}