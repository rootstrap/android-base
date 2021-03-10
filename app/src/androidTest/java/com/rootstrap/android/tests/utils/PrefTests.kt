package com.rootstrap.android.tests.utils

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import com.rootstrap.android.util.Prefs
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.* // ktlint-disable no-wildcard-imports
import javax.inject.Inject

@HiltAndroidTest
class PrefTests {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var prefs: Prefs

    @Inject
    lateinit var preferences: SharedPreferences

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun savingSecureDataPrefs() {
        val uid = UUID.randomUUID().toString()
        prefs.uid = uid

        Assert.assertTrue(preferences is EncryptedSharedPreferences)
        Assert.assertEquals(prefs.uid, uid)
    }
}
