package com.example.interviewtest.case3.di

import android.content.Context
import android.content.SharedPreferences
import com.example.interviewtest.case3.UserResponseDeserialize
import com.example.interviewtest.case3.data.UserLocalRepository
import com.example.interviewtest.case3.data.UserRepository
import com.example.interviewtest.case3.data.UserService
import com.example.interviewtest.case3.domain.UserDTO
import com.example.interviewtest.case3.domain.UserLocalRepositoryImpl
import com.example.interviewtest.case3.domain.UserRepositoryImpl
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideUserService(): UserService {


        return Retrofit.Builder()
            .baseUrl("https://random-data-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userService: UserService): UserRepository {
        return UserRepositoryImpl(userService)

    }


    @Provides
    @Singleton
    fun provideUserLocalRepository(sharedPreferences: SharedPreferences): UserLocalRepository {
        return UserLocalRepositoryImpl(sharedPreferences)
    }


    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }
}
