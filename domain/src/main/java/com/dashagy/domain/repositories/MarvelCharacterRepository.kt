package com.dashagy.domain.repositories

import com.dashagy.domain.entities.MarvelCharacter
import com.dashagy.domain.util.ResultWrapper

interface MarvelCharacterRepository {
    fun getCharacterById(id: Int, getFromRemote: Boolean) : ResultWrapper<List<MarvelCharacter>>
    fun getAllCharacters(getFromRemote: Boolean): ResultWrapper<List<MarvelCharacter>>
}