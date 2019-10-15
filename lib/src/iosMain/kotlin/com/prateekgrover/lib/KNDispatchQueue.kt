package com.prateekgrover.lib

import kotlin.native.concurrent.*

actual class KNDispatchQueue {
    @ThreadLocal
    actual companion object Singleton {
        actual val sharedInstance: KNDispatchQueue = KNDispatchQueue()
    }

    private var workQueue: MutableList<KNWork> = mutableListOf()

    init {
        workQueue.ensureNeverFrozen()
    }

    actual fun performWorkAsync() {
        ::performWork.freeze()
    }

    fun performWork() {

    }
}
