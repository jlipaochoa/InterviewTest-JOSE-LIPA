package com.example.interviewtest.case3.domain

import com.example.interviewtest.case3.data.UserLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(private val userLocalRepository: UserLocalRepository) {
    suspend operator fun invoke(): Flow<Int> {
        return userLocalRepository.getUserId()
    }
}