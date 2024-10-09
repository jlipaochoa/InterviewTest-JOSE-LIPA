package com.example.interviewtest.case3.domain

import com.example.interviewtest.case3.data.UserLocalRepository
import com.example.interviewtest.case3.data.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import javax.inject.Inject

class GetUserCase @Inject constructor(
    val userRepository: UserRepository,
    val userLocalRepository: UserLocalRepository
) {
    suspend operator fun invoke(): UserDTO {
        val user = userRepository.getUsers()
        user.id = userLocalRepository.getUserId().first()
        return user
    }
}