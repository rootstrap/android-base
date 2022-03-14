package com.rootstrap.android

import com.rootstrap.android.network.managers.session.SessionManager
import com.rootstrap.android.network.managers.user.UserManager
import com.rootstrap.android.network.models.User
import com.rootstrap.android.network.models.UserSerializer
import com.rootstrap.android.test.TestDispatcherProvider
import com.rootstrap.android.test.UnitTestBase
import com.rootstrap.android.ui.viewmodel.SignInViewModel
import com.rootstrap.android.ui.viewmodel.SignInState
import com.rootstrap.android.util.NetworkState
import com.rootstrap.android.util.extensions.ApiException
import com.rootstrap.android.util.extensions.Data
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

    private lateinit var viewModel: SignInViewModel

    @RelaxedMockK
    lateinit var sessionManager: SessionManager

    @RelaxedMockK
    lateinit var userManager: UserManager

    @MockK
    lateinit var user: User

    @MockK
    lateinit var userSerializer: UserSerializer

    companion object {
        const val ERROR_EXAMPLE_TEXT = "Time out example"
    }

    @Before
    override fun setup() {
        super.setup()
        every { userSerializer.user } returns user
        viewModel = SignInViewModel(sessionManager, userManager, TestDispatcherProvider())
    }

    // reading: naming standards for unit testing https://osherove.com/blog/2005/4/3/naming-standards-for-unit-tests.html
    @Test
    fun `signIn success assert signInSuccess and network idle`() {
        var state: SignInState? = null
        coEvery { userManager.signIn(user = user) } returns Result.success(Data(userSerializer))

        viewModel.signIn(user)
        viewModel.state.observeForever {
            state = it
        }

        assertEquals(state, SignInState.signInSuccess)
        assertEquals(viewModel.networkState.value, NetworkState.idle)
        verify { sessionManager.signIn(user) }
        coVerify { userManager.signIn(user = user) }
    }

    @Test
    fun `signIn fail assert signInFailure and network error`() {
        var state: SignInState? = null
        coEvery { userManager.signIn(user = user) } returns Result.failure(
            ApiException(
                ERROR_EXAMPLE_TEXT
            )
        )

        viewModel.signIn(user)
        viewModel.state.observeForever {
            state = it
        }

        assertEquals(state, SignInState.signInFailure)
        assertEquals(viewModel.networkState.value, NetworkState.error)
        assertEquals(viewModel.error, ERROR_EXAMPLE_TEXT)
        coVerify { userManager.signIn(user = user) }
    }
}
