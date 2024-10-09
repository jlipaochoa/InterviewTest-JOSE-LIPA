package com.example.interviewtest.case3.domain

import com.example.interviewtest.case3.data.UserLocalRepository
import javax.inject.Inject

class AddUserIdUseCase @Inject constructor(private val userLocalRepository: UserLocalRepository) {
    suspend operator fun invoke(id:Int): Int {
        return userLocalRepository.setUserId(id + 1)
    }
}