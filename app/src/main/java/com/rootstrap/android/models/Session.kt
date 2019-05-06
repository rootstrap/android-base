package com.rootstrap.android.models

import com.rootstrap.android.App

fun Session.save() = App.mE.saveSession()

class Session(var uid: String, var profile: Profile, var open: Boolean = false)