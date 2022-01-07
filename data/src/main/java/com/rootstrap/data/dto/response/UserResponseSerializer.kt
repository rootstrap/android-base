package com.rootstrap.data.dto.response

import com.google.gson.annotations.SerializedName

// TODO Discuss class name on Android Roundtable (UserDTO - DTO = Data Transfer Object as suffix)
data class User(
    val id: String = "",
    var email: String = "",
    @SerializedName("first_name") var firstName: String = "",
    @SerializedName("last_name") var lastName: String = "",
    @SerializedName("phone_number") var phoneNumber: String = "",
    val password: String = "",
    val username: String = "",
    val uid: String?
)

data class UserResponseSerializer(val user: User)
