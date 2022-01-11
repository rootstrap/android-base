package com.rootstrap.data.dto.response

import com.google.gson.annotations.SerializedName
import com.rootstrap.domain.User

data class UserDTO(
    val id: String = "",
    var email: String = "",
    @SerializedName("first_name") var firstName: String = "",
    @SerializedName("last_name") var lastName: String = "",
    @SerializedName("phone_number") var phoneNumber: String = "",
    val password: String = "",
    val username: String = "",
    val uid: String?
)

fun UserDTO.toDomainUser() = User(
    id = id,
    email = email,
    firstName = firstName,
    lastName = lastName,
    phoneNumber = phoneNumber,
    password = password,
    username = username
)

data class UserResponseSerializer(val user: UserDTO)
