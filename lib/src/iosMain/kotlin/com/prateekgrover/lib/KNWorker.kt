package com.prateekgrover.lib

import kotlin.native.concurrent.Worker

class KNWorker {
    var isWorking: Boolean = false
    var worker: Worker? = null

    init {
        isWorking = false
        worker = Worker.start()
    }
}