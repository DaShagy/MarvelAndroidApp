package com.dashagy.data.database

import com.dashagy.data.database.entities.MarvelCharacterRealm
import com.dashagy.data.mappers.CharacterMapperLocal
import com.dashagy.domain.entities.MarvelCharacter
import com.dashagy.domain.util.ResultWrapper
import io.realm.Case
import io.realm.Realm

class CharacterDatabase {

    private val localMapper = CharacterMapperLocal()

    fun getCharacterById(id: Int): ResultWrapper<List<MarvelCharacter>> {
        Realm.getDefaultInstance().use {
            val character = it.where(MarvelCharacterRealm::class.java).equalTo("id", id).findFirst()
            character?.let { return ResultWrapper.Success(listOf(localMapper.transform(character))) }
            return ResultWrapper.Failure(Exception("Character not found"))
        }
    }

    fun getAllCharacters(): ResultWrapper<List<MarvelCharacter>>{
        Realm.getDefaultInstance().use {
            val characterList = it.where(MarvelCharacterRealm::class.java).sort("name").findAll()
            characterList?.let {
                return ResultWrapper.Success(characterList.map { character ->
                    localMapper.transform(character)
                })
            }
        }
        return ResultWrapper.Failure(Exception("Character not found"))
    }

    fun getCharactersByName(name: String): ResultWrapper<List<MarvelCharacter>> {
        Realm.getDefaultInstance().use {
            val characterList = it.where(MarvelCharacterRealm::class.java).beginsWith("name", name, Case.INSENSITIVE).findAll()
            characterList?.let {
                return ResultWrapper.Success(characterList.map { character ->
                    localMapper.transform(character)
            }) }
            return ResultWrapper.Failure(Exception("Character not found"))
        }
    }

    fun insertOrUpdateCharacter(character: MarvelCharacter) {
        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                realm.insertOrUpdate(localMapper.transformToRepository(character))
            }
        }
    }
}