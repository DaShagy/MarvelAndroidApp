package com.dashagy.domain.usecases

import com.dashagy.domain.repositories.MarvelCharacterRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetCharactersByNameUseCase : KoinComponent {
    private val marvelCharacterRepository: MarvelCharacterRepository by inject()
    operator fun invoke(name: String, getFromRemote: Boolean) = marvelCharacterRepository.getCharactersByName(name, getFromRemote)
}