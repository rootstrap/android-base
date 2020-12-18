package com.rootstrap.android.network.models

import com.squareup.moshi.Json

data class User(
    @Json(name = "id") val id: String = "",
    @Json(name = "email") var email: String = "",
    @Json(name = "first_name") var firstName: String = "",
    @Json(name = "last_name") var lastName: String = "",
    @Json(name = "phone_number") var phone: String = "",
    @Json(name = "password") val password: String = "",
    @Json(name = "username") val username: String = ""
)

data class UserSerializer(@Json(name = "user") val user: User)
