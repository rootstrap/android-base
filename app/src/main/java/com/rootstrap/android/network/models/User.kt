package com.rootstrap.android.network.models

import com.squareup.moshi.Json

data class User(
    @Json(name = "id") val id: String = "",
    @Json(name = "email") var email: String = "",
    @Json(name = "name") var name: String = "",
    @Json(name = "phone_number") var phone: String = "",
    @Json(name = "password") val password: String = ""
)

data class UserSerializer(val user: User)
