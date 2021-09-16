package com.dashagy.data.database

import com.dashagy.data.database.entities.MarvelCharacterRealm
import com.dashagy.data.mappers.CharacterMapperLocal
import com.dashagy.domain.entities.MarvelCharacter
import com.dashagy.domain.util.ResultWrapper
import io.realm.Realm

class CharacterDatabase {

    fun getCharacterById(id: Int): ResultWrapper<MarvelCharacter> {
        val mapper = CharacterMapperLocal()
        Realm.getDefaultInstance().use {
            val character = it.where(MarvelCharacterRealm::class.java).equalTo("id", id).findFirst()
            character?.let { return ResultWrapper.Success(mapper.transform(character)) }
            return ResultWrapper.Failure(Exception("Character not found"))
        }
    }

    fun insertOrUpdateCharacter(character: MarvelCharacter) {
        val mapperLocal = CharacterMapperLocal()
        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                realm.insertOrUpdate(mapperLocal.transformToRepository(character))
            }
        }
    }
}