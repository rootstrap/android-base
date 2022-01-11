package com.rootstrap.android

import com.rootstrap.android.test.TestDispatcherProvider
import com.rootstrap.android.test.UnitTestBase
import com.rootstrap.android.ui.login.SignInActivityViewModel
import com.rootstrap.android.ui.login.SignInState
import com.rootstrap.android.util.NetworkState
import com.rootstrap.data.api.ApiException
import com.rootstrap.data.dto.request.UserSignInRequest
import com.rootstrap.data.dto.request.UserSignInRequestSerializer
import com.rootstrap.data.dto.response.DataResult
import com.rootstrap.data.dto.response.UserResponseSerializer
import com.rootstrap.data.managers.session.SessionManager
import com.rootstrap.usecases.SignIn
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

@ExperimentalCoroutinesApi
class SignInActivityViewModelTest : UnitTestBase() {

    private lateinit var viewModel: SignInActivityViewModel

    @RelaxedMockK
    lateinit var sessionManager: SessionManager

    @RelaxedMockK
    lateinit var signIn: SignIn

    @MockK
    lateinit var userSignInRequestSerializer: UserSignInRequestSerializer

    @MockK
    lateinit var userSignInResponseSerializer: UserResponseSerializer

    @MockK
    lateinit var userSignInRequest: UserSignInRequest

    companion object {
        const val ERROR_EXAMPLE_TEXT = "Time out example"
    }

    @Before
    override fun setup() {
        super.setup()
        every { userSignInRequestSerializer.user } returns userSignInRequest
        every { userSignInResponseSerializer.user } returns userDTO
        viewModel = SignInActivityViewModel(
            signIn,
            sessionManager,
            TestDispatcherProvider()
        ) // ver q hacer con los dispatchers
    }

    // reading: naming standards for unit testing https://osherove.com/blog/2005/4/3/naming-standards-for-unit-tests.html
    @Test
    fun `signIn success assert signInSuccess and network idle`() {
        var state: SignInState? = null
        coEvery { signIn.invoke(any()) } returns DataResult.Success(
            userSignInResponseSerializer
        )

        viewModel.signIn(userSignInRequest)
        viewModel.state.observeForever {
            state = it
        }

        assertEquals(state, SignInState.SIGN_IN_SUCCESS)
        assertEquals(viewModel.networkState.value, NetworkState.IDLE)
        verify { sessionManager.signIn(any()) }
        coVerify { signIn.invoke(any()) }
    }

    @Test
    fun `signIn fail assert signInFailure and network error`() {
        var state: SignInState? = null
        coEvery { signIn.invoke(any()) } returns DataResult.Error(
            ApiException(
                ERROR_EXAMPLE_TEXT
            )
        )

        viewModel.signIn(userSignInRequest)
        viewModel.state.observeForever {
            state = it
        }

        assertEquals(state, SignInState.SIGN_IN_FAILURE)
        assertEquals(viewModel.networkState.value, NetworkState.ERROR)
        assertEquals(viewModel.error, ERROR_EXAMPLE_TEXT)
        coVerify { signIn.invoke(any()) }
    }
}
