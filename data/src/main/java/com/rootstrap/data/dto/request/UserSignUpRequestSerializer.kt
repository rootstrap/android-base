package com.rootstrap.data.dto.request

import com.google.gson.annotations.SerializedName

data class UserSignUpRequest(
    var email: String = "",
    @SerializedName("first_name") var firstName: String = "",
    @SerializedName("last_name") var lastName: String = "",
    val username: String = "",
    val password: String = ""
)

data class UserSignUpRequestSerializer(val UserSignUpRequest: UserSignUpRequest)
