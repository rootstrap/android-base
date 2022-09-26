package com.rootstrap.usecases

import com.rootstrap.data.dto.request.UserSignUpRequestSerializer
import com.rootstrap.data.dto.response.DataResult
import com.rootstrap.data.dto.response.UserResponseSerializer
import com.rootstrap.data.repository.UserRepository

class SignUp(private val userRepository: UserRepository) {

    suspend fun invoke(userSignUpRequestSerializer: UserSignUpRequestSerializer): DataResult<UserResponseSerializer> =
        userRepository.signUp(userSignUpRequestSerializer)
}
