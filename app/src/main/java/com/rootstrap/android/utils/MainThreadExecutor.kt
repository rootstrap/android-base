package com.rootstrap.android.utils

/**
 * @author Amaury Ricardo Miranda 2019
 * */

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

class MainThreadExecutor : Executor {

    private val mainThreadHandler : Handler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable) {
        mainThreadHandler.post(command)
    }
}
