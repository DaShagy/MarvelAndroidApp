package com.dashagy.domain.usecases

import com.dashagy.domain.repositories.MarvelCharacterRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetCharacterByIdUseCase : KoinComponent {
    private val marvelCharacterRepository: MarvelCharacterRepository by inject()
    operator fun invoke(id: Int, getFromRemote: Boolean) = marvelCharacterRepository.getCharacterById(id, getFromRemote)
}