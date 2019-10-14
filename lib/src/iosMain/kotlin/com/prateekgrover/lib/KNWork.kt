package com.prateekgrover.lib

class KNWork(identity: Int, workFunction: () -> Unit) {
    val id: Int = identity
    var workerFunction: () -> Unit = workFunction
}