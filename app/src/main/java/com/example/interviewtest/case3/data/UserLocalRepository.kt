package com.example.interviewtest.case3.data

import kotlinx.coroutines.flow.Flow

interface UserLocalRepository {
    suspend fun setUserId(id:Int): Int
    suspend fun getUserId(): Flow<Int>
}