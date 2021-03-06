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

    fun getCharacterById(id: Int): ResultWrapper<List<MarvelCharacter>> {
        val callResponse = api.createService(MarvelApi::class.java).getCharacterById(id)
        val response = callResponse.execute()
        if (response != null) {
            if (response.isSuccessful) {
                response.body()?.data?.characters?.get(ZERO)?.let { mapper.transform(it) }?.let { return ResultWrapper.Success(listOf(it)) }
            }
            return ResultWrapper.Failure(Exception(response.message()))
        }
        return ResultWrapper.Failure(Exception("Bad request/response"))
    }

    private fun getCharacters(offset: Int, limit: Int, name: String): ResultWrapper<List<MarvelCharacter>> {

        val filter = HashMap<String, String>()
        filter["offset"] = offset.toString()
        filter["limit"] = limit.toString()
        if (name.isNotBlank()) filter["nameStartsWith"] = name

        val callResponse = api.createService(MarvelApi::class.java).getAllCharacters(filter)
        val response = callResponse.execute()
        if (response != null){
            if (response.isSuccessful){
                response.body()?.data?.characters.let { characters -> characters?.map { mapper.transform(it)}}?.let { return ResultWrapper.Success(it) }
            }
            return ResultWrapper.Failure(Exception(response.message()))
        }
        return ResultWrapper.Failure(Exception("Bad request/response"))
    }

    // Function return every character in service/database. Could be refactored

    fun getAllCharacters(name: String = ""): ResultWrapper<List<MarvelCharacter>> {
        var offset = 0
        val limit = 100
        val resultList: MutableList<MarvelCharacter> = mutableListOf()

        while (true){
            when (val call = getCharacters(offset, limit, name)){
                is ResultWrapper.Success -> {
                    if (call.data.isNotEmpty()){
                        offset += limit
                        resultList.addAll(call.data)
                    } else break
                }
                is ResultWrapper.Failure -> {
                    return ResultWrapper.Failure(Exception("Bad request/response"))
                }
            }
        }

        return ResultWrapper.Success(resultList as List<MarvelCharacter>)
    }
}