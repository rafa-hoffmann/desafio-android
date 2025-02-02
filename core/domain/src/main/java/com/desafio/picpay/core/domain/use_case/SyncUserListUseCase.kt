package com.desafio.picpay.core.domain.use_case

import com.desafio.picpay.core.data.repository.UserRepository

class SyncUserListUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() = userRepository.sync()
}