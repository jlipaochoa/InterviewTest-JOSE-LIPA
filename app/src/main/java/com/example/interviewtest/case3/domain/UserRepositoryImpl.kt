package com.example.interviewtest.case3.domain

import com.example.interviewtest.case3.data.UserRepository
import com.example.interviewtest.case3.data.UserService
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val userService: UserService) : UserRepository {
    override suspend fun getUsers(): UserDTO {
        return userService.getUser()
    }
}