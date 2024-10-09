package com.example.interviewtest.case3.data

import com.example.interviewtest.case3.domain.UserDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

interface UserRepository {
    suspend fun getUsers(): UserDTO
}