package com.rootstrap.android

import com.rootstrap.android.network.managers.session.SessionManager
import com.rootstrap.android.network.managers.user.UserManager
import com.rootstrap.android.network.models.User
import com.rootstrap.android.network.models.UserSerializer
import com.rootstrap.android.test.TestDispatcherProvider
import com.rootstrap.android.test.UnitTestBase
import com.rootstrap.android.ui.viewmodel.SignUpViewModel
import com.rootstrap.android.ui.viewmodel.SignUpState
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

@ExperimentalCoroutinesApi // This annotation is required to use TestDispatcherProvider is still an experiment
class SignUpActivityViewModelTest : UnitTestBase() {

    private lateinit var viewModel: SignUpViewModel

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
        viewModel = SignUpViewModel(sessionManager, userManager, TestDispatcherProvider())
    }

    @Test
    fun `signUp success assert signUpSuccess and network idle`() {
        var state: SignUpState? = null
        coEvery { userManager.signUp(user = user) } returns Result.success(Data(userSerializer))

        viewModel.signUp(user)
        viewModel.state.observeForever {
            state = it
        }

        assertEquals(state, SignUpState.signUpSuccess)
        assertEquals(viewModel.networkState.value, NetworkState.idle)
        verify { sessionManager.signIn(user) }
        coVerify { userManager.signUp(user = user) }
    }

    @Test
    fun `signUp fail assert signUpFailure and network error`() {
        var state: SignUpState? = null
        coEvery { userManager.signUp(user = user) } returns Result.failure(
            ApiException(
                ERROR_EXAMPLE_TEXT
            )
        )

        viewModel.signUp(user)
        viewModel.state.observeForever {
            state = it
        }

        assertEquals(state, SignUpState.signUpFailure)
        assertEquals(viewModel.networkState.value, NetworkState.error)
        assertEquals(viewModel.error, ERROR_EXAMPLE_TEXT)
        coVerify { userManager.signUp(user = user) }
    }
}
