package com.prateekgrover.lib

class Greeting() {
    fun start() {
        KNDispatchQueue.sharedInstance.performWorkAsync()
    }
}

expect class KNDispatchQueue() {
    companion object Singleton {
        val sharedInstance: KNDispatchQueue
    }

    fun performWorkAsync()
}
