package com.dashagy.data.repositories

import com.dashagy.data.database.CharacterDatabase
import com.dashagy.data.service.CharacterService
import com.dashagy.domain.entities.MarvelCharacter
import com.dashagy.domain.repositories.MarvelCharacterRepository
import com.dashagy.domain.util.ResultWrapper

class MarvelCharacterRepositoryImpl(
    private val characterService: CharacterService,
    private val characterDatabase: CharacterDatabase
) : MarvelCharacterRepository {

    override fun getCharacterById(id: Int, getFromRemote: Boolean): ResultWrapper<List<MarvelCharacter>> =
        if (getFromRemote) {
            val marvelCharacterResult = characterService.getCharacterById(id)
            if (marvelCharacterResult is ResultWrapper.Success) {
                insertOrUpdateCharacter(marvelCharacterResult.data[0])
            }
            marvelCharacterResult
        } else {
            characterDatabase.getCharacterById(id)
        }

    override fun getAllCharacters(getFromRemote: Boolean): ResultWrapper<List<MarvelCharacter>> =
        if (getFromRemote) {
            val marvelCharacterResultList = characterService.getAllCharacters()
            if (marvelCharacterResultList is ResultWrapper.Success){
                marvelCharacterResultList.data.map { insertOrUpdateCharacter(it)}
            }
            marvelCharacterResultList
        } else {
            characterDatabase.getAllCharacters()
        }

    private fun insertOrUpdateCharacter(character: MarvelCharacter) {
        characterDatabase.insertOrUpdateCharacter(character)
    }
}