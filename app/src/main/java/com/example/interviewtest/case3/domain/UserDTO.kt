package com.example.interviewtest.case3.domain

import com.google.gson.annotations.SerializedName

class UserDTO(
    var id: Int,
    val uid: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val email: String,
    val avatar: String,
    val gender: String,
    val phoneNumber: String,
)

class ResponseGetUserDTO(
    val data: List<UserDTO>
)