package com.rootstrap.android.test

import com.rootstrap.android.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
class TestDispatcherProvider(testCoroutineDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()) :
    DispatcherProvider {
    override val default: CoroutineDispatcher = testCoroutineDispatcher
    override val main: CoroutineDispatcher = testCoroutineDispatcher
    override val io: CoroutineDispatcher = testCoroutineDispatcher
    override val unconfined: CoroutineDispatcher = testCoroutineDispatcher
}
