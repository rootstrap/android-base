package com.rootstrap.android.tests

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.rootstrap.android.R
import com.rootstrap.android.ui.activity.MainActivity
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
class ProfileFragmentTest : BaseTests() {

    private lateinit var activity: MainActivity
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    override fun before() {
        super.before()
        setServerDispatch(logoutDispatcher())
        sessionManager.user = testUser()
        scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.onActivity { activity -> this.activity = activity }
    }

    @Test
    fun profileUiTest() {
        stringMatches(
            R.id.welcome_text_view,
            activity.getString(R.string.welcome_message, sessionManager.user?.firstName)
        )
        onView(withId(R.id.sign_out_button)).perform(click())
        assertEquals(null, sessionManager.user)

        // Check if this activity was successful launched
        activity.runOnUiThread {
            val current = currentActivity()
            assertEquals(MainActivity::class.java.name, current::class.java.name)
        }
    }

    private fun logoutDispatcher(): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return if (request.path!!.contains("users/sign_out"))
                    mockServer.successfulResponse()
                else
                    mockServer.notFoundResponse()
            }
        }
    }

    @After
    override fun after() {
        super.after()
    }
}
