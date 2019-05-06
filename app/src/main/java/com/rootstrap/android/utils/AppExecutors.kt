package com.rootstrap.android.utils

import java.util.concurrent.Executor

/**
 * @author Amaury Ricardo Miranda 2019
 *
 *  Background thread
 *  ioThread.execute {
 *       //action
 *  }
 *
 *  Main thread
 *  mainThread.execute {
 *       //action
 *  }
 * */

var ioThread: Executor = IoThreadExecutor()
var mainThread: Executor = MainThreadExecutor()

