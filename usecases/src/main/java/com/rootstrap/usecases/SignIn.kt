package com.rootstrap.usecases

import com.rootstrap.data.dto.request.UserSignInRequestSerializer
import com.rootstrap.data.dto.response.DataResult
import com.rootstrap.data.dto.response.UserResponseSerializer
import com.rootstrap.data.repository.UserRepository

class SignIn(private val userRepository: UserRepository) {

    suspend fun invoke(userSignInRequestSerializer: UserSignInRequestSerializer): DataResult<UserResponseSerializer> =
        userRepository.signIn(userSignInRequestSerializer)
}
