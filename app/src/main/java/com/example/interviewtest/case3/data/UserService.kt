package com.example.interviewtest.case3.data

import com.example.interviewtest.case3.domain.UserDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("api/users/random_user")
    suspend fun getUser(): UserDTO

}