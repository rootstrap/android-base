package com.rootstrap.android.tests.utils

import android.content.SharedPreferences
import com.rootstrap.android.util.Prefs
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import org.junit.Assert
import org.junit.Test
import java.util.UUID

class PrefsTest {

    @Test
    fun savingSecureDataPrefs() {
        val uid = UUID.randomUUID().toString()
        val preferences = mockk<SharedPreferences>()
        val prefs = spyk(Prefs(preferences), recordPrivateCalls = true)
        every { preferences.edit().putString("uid", uid).apply() } returns Unit
        every { preferences.getString("uid", "")!! } returns uid
        every { prefs ["getPref"]() } returns preferences

        prefs.uid = uid

        Assert.assertEquals(prefs.uid, uid)
    }
}
