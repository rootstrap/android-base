package com.rootstrap.android.test

import androidx.annotation.CallSuper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule

abstract class UnitTestBase {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @CallSuper
    @Before
    open fun setup() {
        MockKAnnotations.init(this)
    }
}
