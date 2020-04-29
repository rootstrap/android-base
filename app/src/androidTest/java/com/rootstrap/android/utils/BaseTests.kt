package com.rootstrap.android.utils

import android.app.Activity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.* // ktlint-disable no-wildcard-imports
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.rootstrap.android.network.managers.SessionManager
import com.rootstrap.android.network.managers.UserManager
import com.rootstrap.android.network.models.User
import okhttp3.mockwebserver.Dispatcher
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
open class BaseTests {

    var mockServer: MockServer = MockServer

    open fun setServerDispatch(dispatcher: Dispatcher) {
        mockServer.server().dispatcher = dispatcher
    }

    open fun before() {
        mockServer.startServer()
        UserManager.reloadService(mockServer.server().url("/").toString())
    }

    open fun after() {
        mockServer.stopServer()
    }

    open fun setupSession() {
        SessionManager.user = testUser()
    }

    open fun testUser() = User(
        "9032",
        "user123@mail.com",
        "Richard",
        "Richard",
        "99090909",
        "asdasdasdasda",
        "Richard"
    )

    open fun scrollAndTypeText(id: Int, text: String) {
        onView(withId(id)).perform(
            scrollTo(),
            click(),
            clearText(),
            typeText(text),
            closeSoftKeyboard()
        )
    }

    open fun typeText(id: Int, text: String) {
        onView(withId(id)).perform(click(), clearText(), typeText(text), closeSoftKeyboard())
    }

    open fun scrollAndPerformClick(viewId: Int) {
        onView(withId(viewId)).perform(scrollTo(), click())
    }

    open fun performClick(viewId: Int) {
        onView(withId(viewId)).perform(click())
    }

    open fun stringMatches(viewId: Int, value: String) {
        onView(withId(viewId)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    value
                )
            )
        )
    }

    open fun currentActivity(): Activity {
        // Get the activity that currently started
        val activities =
            ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED)
        return activities.first()
    }
}
