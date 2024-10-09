package com.example.interviewtest.case3

import com.example.interviewtest.case3.domain.UserDTO
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement

class UserResponseDeserialize : JsonDeserializer<List<UserDTO>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: java.lang.reflect.Type?,
        context: JsonDeserializationContext
    ): List<UserDTO> {
        val result = mutableListOf<UserDTO>()

        if (json.isJsonArray) {
            json.asJsonArray.forEach { jsonElement ->
                result.add(context.deserialize(jsonElement, UserDTO::class.java))
            }
        } else if (json.isJsonObject) {
            result.add(context.deserialize(json.asJsonObject, UserDTO::class.java))
        }

        return result
    }

}