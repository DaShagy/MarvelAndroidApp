package com.dashagy.data.database.response

import com.dashagy.data.service.CharacterResponse
import com.google.gson.annotations.SerializedName

class DatabaseResponse<T>(
        @SerializedName("results") val characters: List<CharacterResponse>,
        val offset: Int,
        val limit: Int,
        val total: Int
)
