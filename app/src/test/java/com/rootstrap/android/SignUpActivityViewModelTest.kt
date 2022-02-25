package com.rootstrap.android

import com.rootstrap.android.test.TestDispatcherProvider
import com.rootstrap.android.test.UnitTestBase
import com.rootstrap.android.ui.login.SignUpActivityViewModel
import com.rootstrap.android.ui.login.SignUpState
import com.rootstrap.android.util.NetworkState
import com.rootstrap.data.api.ApiException
import com.rootstrap.data.dto.request.UserSignUpRequest
import com.rootstrap.data.dto.response.DataResult
import com.rootstrap.data.dto.response.UserResponseSerializer
import com.rootstrap.data.managers.session.SessionManager
import com.rootstrap.usecases.SignUp
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi // This annotation is required to use TestDispatcherProvider is still an experiment
class SignUpActivityViewModelTest : UnitTestBase() {

    private lateinit var viewModel: SignUpActivityViewModel

    @RelaxedMockK
    lateinit var sessionManager: SessionManager

    @RelaxedMockK
    lateinit var signUp: SignUp

    @MockK
    lateinit var userResponseSerializer: UserResponseSerializer

    @MockK
    lateinit var userSignUpRequest: UserSignUpRequest

    companion object {
        const val ERROR_EXAMPLE_TEXT = "Time out example"
    }

    @Before
    override fun setup() {
        super.setup()
        every { userResponseSerializer.user } returns userDTO
        viewModel = SignUpActivityViewModel(signUp, sessionManager, TestDispatcherProvider())
    }

    @Test
    fun `signUp success assert signUpSuccess and network idle`() {
        var state: SignUpState? = null
        coEvery { signUp.invoke(any()) } returns DataResult.Success(
            userResponseSerializer
        )

        viewModel.signUp(userSignUpRequest)
        viewModel.state.observeForever {
            state = it
        }

        assertEquals(state, SignUpState.SIGN_UP_SUCCESS)
        assertEquals(viewModel.networkState.value, NetworkState.IDLE)
        verify { sessionManager.signIn(any()) }
        coVerify { signUp.invoke(any()) }
    }

    @Test
    fun `signUp fail assert signUpFailure and network error`() {
        var state: SignUpState? = null
        coEvery { signUp.invoke(any()) } returns DataResult.Error(
            ApiException(
                ERROR_EXAMPLE_TEXT
            )
        )

        viewModel.signUp(userSignUpRequest)
        viewModel.state.observeForever {
            state = it
        }

        assertEquals(state, SignUpState.SIGN_UP_FAILURE)
        assertEquals(viewModel.networkState.value, NetworkState.ERROR)
        assertEquals(viewModel.error, ERROR_EXAMPLE_TEXT)
        coVerify { signUp.invoke(any()) }
    }
}
