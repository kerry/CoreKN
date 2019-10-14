package com.prateekgrover.lib

import kotlinx.cinterop.COpaquePointer
import platform.posix.sleep
import kotlin.native.concurrent.*

actual class KNDispatchQueue {
    @ThreadLocal
    actual companion object Singleton {
        actual val sharedInstance: KNDispatchQueue = KNDispatchQueue()
    }

    val observerWorker: Worker = Worker.start().freeze()
    internal var workQueue: COpaquePointer? = DetachedObjectGraph(TransferMode.SAFE) { mutableListOf<KNWork>() }.asCPointer()

    init {
        println("kndispatch queue initialized")
    }

    actual fun performWorkAsync() {
        // works with 1000 iterations but not with 2000
        for (i in 1..2000) {
            val task = {
                val detachedWorkQueue = DetachedObjectGraph<MutableList<KNWork>>(workQueue).attach()
                val success = detachedWorkQueue.add(KNWork(i) { sleep(2) })
            }.freeze()
            observerWorker.executeAfter(0, task)
        }

        // running this multiple times causes problems
//        for (i in 1..10000) {
//            observerWorker.execute(TransferMode.SAFE, {
//                workQueue
//            }) {
//                val detachedWorkQueue = DetachedObjectGraph<MutableList<KNWork>>(it).attach()
//            }
//        }
    }
}
