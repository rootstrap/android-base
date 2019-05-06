package com.rootstrap.android.utils

/**
 * @author Amaury Ricardo Miranda 2019
 * */

import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Executor that runs a task on a new background thread.
 */
class IoThreadExecutor : Executor {

    private val mDiskIO: Executor

    init {
        mDiskIO = Executors.newSingleThreadExecutor()
    }

    override fun execute(command: Runnable) {
        mDiskIO.execute(command)
    }
}
