package com.rootstrap.android.tests

import androidx.test.core.app.ActivityScenario
import com.google.gson.Gson
import com.rootstrap.android.R
import com.rootstrap.android.network.models.UserSerializer
import com.rootstrap.android.ui.activity.MainActivity
import com.rootstrap.android.ui.activity.OnBoardingActivity
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
class SignUpFragmentTest : BaseTests() {

    private lateinit var activity: OnBoardingActivity
    private lateinit var scenario: ActivityScenario<OnBoardingActivity>

    @Before
    override fun before() {
        super.before()
        scenario = ActivityScenario.launch(OnBoardingActivity::class.java)
        scenario.onActivity { activity -> this.activity = activity }
        scenario.recreate()
    }

    @Test
    fun signUpSuccessfulTest() {
        scenario.recreate()
        setServerDispatch(signUpDispatcher())
        val testUser = testUser()
        scrollAndTypeText(R.id.first_name_edit_text, testUser.firstName)
        scrollAndTypeText(R.id.last_name_edit_text, testUser.lastName)
        scrollAndTypeText(R.id.email_edit_text, testUser.email)
        scrollAndTypeText(R.id.password_edit_text, testUser.password)
        scrollAndPerformClick(R.id.sign_up_button)
        val user = sessionManager.user
        assertEquals(user?.email, testUser.email)

        activity.runOnUiThread {
            val current = currentActivity()
            assertEquals(MainActivity::class.java.name, current::class.java.name)
        }
    }

    private fun signUpDispatcher(): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return if (request.path!!.contains("users")) {
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
