package com.dashagy.data.mappers

import com.dashagy.data.database.entities.MarvelCharacterRealm
import com.dashagy.domain.entities.MarvelCharacter

class CharacterMapperLocal : BaseMapperRepository<MarvelCharacterRealm, MarvelCharacter> {
    override fun transform(type: MarvelCharacterRealm) =
        MarvelCharacter(
            type.id,
            type.name,
            type.description,
            type.image
        )

    override fun transformToRepository(type: MarvelCharacter) =
        MarvelCharacterRealm(
            type.id,
            type.name,
            type.description,
            type.image
        )
}