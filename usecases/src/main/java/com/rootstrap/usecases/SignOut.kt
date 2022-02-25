package com.rootstrap.usecases

import com.rootstrap.data.dto.response.DataResult
import com.rootstrap.data.repository.UserRepository

class SignOut(private val userRepository: UserRepository) {

    suspend fun invoke(): DataResult<Void> =
        userRepository.signOut()
}
