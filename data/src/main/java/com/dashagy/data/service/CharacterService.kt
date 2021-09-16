package com.dashagy.data.service

import com.dashagy.data.MarvelRequestGenerator
import com.dashagy.data.ZERO
import com.dashagy.data.mappers.CharacterMapperService
import com.dashagy.data.service.api.MarvelApi
import com.dashagy.domain.entities.MarvelCharacter
import com.dashagy.domain.util.ResultWrapper

class CharacterService {
    private val api: MarvelRequestGenerator = MarvelRequestGenerator()
    private val mapper: CharacterMapperService = CharacterMapperService()

    fun getCharacterById(id: Int): ResultWrapper<MarvelCharacter> {
        val callResponse = api.createService(MarvelApi::class.java).getCharacterById(id)
        val response = callResponse.execute()
        if (response != null) {
            if (response.isSuccessful) {
                response.body()?.data?.characters?.get(ZERO)?.let { mapper.transform(it) }?.let { return ResultWrapper.Success(it) }
            }
            return ResultWrapper.Failure(Exception(response.message()))
        }
        return ResultWrapper.Failure(Exception("Bad request/response"))
    }
}