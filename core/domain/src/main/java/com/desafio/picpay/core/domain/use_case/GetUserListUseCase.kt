package com.desafio.picpay.core.domain.use_case

import com.desafio.picpay.core.data.model.User
import com.desafio.picpay.core.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserListUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<List<User>> = userRepository.getUsers()
}