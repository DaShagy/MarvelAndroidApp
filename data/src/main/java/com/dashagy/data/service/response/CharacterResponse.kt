package com.dashagy.data.service

data class CharacterResponse(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Image
)

data class Image(
    val path: String,
    val extension: String
)
