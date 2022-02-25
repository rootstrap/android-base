package com.rootstrap.android.test

import androidx.annotation.CallSuper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rootstrap.data.dto.response.UserDTO
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule

abstract class UnitTestBase {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    val userDTO: UserDTO = UserDTO(
        "1",
        "as@rs.com",
        "joseph",
        "GG",
        "1234",
        "12345",
        "as",
        ""
    )

    @CallSuper
    @Before
    open fun setup() {
        MockKAnnotations.init(this)
    }
}
