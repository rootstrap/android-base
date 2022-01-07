package com.rootstrap.data.dto.request

data class UserSignInRequest(
    var email: String = "",
    val password: String = ""
)

data class UserSignInRequestSerializer(val userSignInRequest: UserSignInRequest)
