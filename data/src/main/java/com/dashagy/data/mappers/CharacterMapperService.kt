package com.dashagy.data.mappers

import com.dashagy.data.EMPTY_STRING
import com.dashagy.data.service.CharacterResponse
import com.dashagy.data.service.Image
import com.dashagy.domain.entities.MarvelCharacter

class CharacterMapperService : BaseMapperRepository<CharacterResponse, MarvelCharacter> {
    override fun transform(type: CharacterResponse) =
        MarvelCharacter(
            type.id,
            type.name,
            type.description,
            type.thumbnail.path + type.thumbnail.extension
        )

    override fun transformToRepository(type: MarvelCharacter) =
        CharacterResponse(
            type.id,
            type.name,
            type.description,
            Image(EMPTY_STRING, EMPTY_STRING)
        )
}
