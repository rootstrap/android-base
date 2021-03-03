package com.rootstrap.android.tests

import androidx.test.core.app.ActivityScenario
import com.google.gson.Gson
import com.rootstrap.android.R
import com.rootstrap.android.network.models.UserSerializer
import com.rootstrap.android.ui.activity.main.ProfileActivity
import com.rootstrap.android.ui.activity.main.SignInActivity
import com.rootstrap.android.utils.BaseTests
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@HiltAndroidTest
class SignInActivityTest : BaseTests() {

    private lateinit var activity: SignInActivity
    private lateinit var scenario: ActivityScenario<SignInActivity>

    @Before
    override fun before() {
        super.before()
        scenario = ActivityScenario.launch(SignInActivity::class.java)
        scenario.onActivity { activity -> this.activity = activity }
    }

    @Test
    fun signInSuccessfulTest() {
        scenario.recreate()
        setServerDispatch(signInDispatcher())
        val testUser = testUser()
        typeText(R.id.email_edit_text, testUser.email)
        typeText(R.id.password_edit_text, testUser.password)
        performClick(R.id.sign_in_button)
        val user = sessionManager.user
        assertEquals(user, testUser)

        activity.runOnUiThread {
            val current = currentActivity()
            assertEquals(ProfileActivity::class.java.name, current::class.java.name)
        }
    }

    private fun signInDispatcher(): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return if (request.path!!.contains("users/sign_in")) {
                    val userResponse = UserSerializer(testUser())
                    mockServer.successfulResponse().setBody(
                        Gson().toJson(userResponse)
                    )
                } else
                    mockServer.notFoundResponse()
            }
        }
    }

    @After
    override fun after() {
        super.after()
    }
}
