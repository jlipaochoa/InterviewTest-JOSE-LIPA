package com.example.interviewtest.case3.domain

import android.content.SharedPreferences
import android.util.Log
import com.example.interviewtest.case3.data.UserLocalRepository
import com.example.interviewtest.case3.presentation.USER_ID
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import javax.inject.Inject

class UserLocalRepositoryImpl @Inject constructor(val sharedPreferences: SharedPreferences) :
    UserLocalRepository {
    override suspend fun setUserId(id: Int): Int {
        sharedPreferences.edit().putInt(USER_ID, id).apply()
        return 1
    }

    override suspend fun getUserId(): Flow<Int> = callbackFlow {
        val userId = sharedPreferences.getInt(USER_ID, 1)
        trySend(userId).isSuccess

        val listener =
            SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
                if (key == USER_ID) {
                    trySend(sharedPreferences.getInt(USER_ID, 1)).isSuccess
                }
            }

        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)

        awaitClose {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }
}