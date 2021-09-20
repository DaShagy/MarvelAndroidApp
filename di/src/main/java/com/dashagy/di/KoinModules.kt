package com.dashagy.di

import com.dashagy.data.database.CharacterDatabase
import com.dashagy.data.mappers.CharacterMapperLocal
import com.dashagy.data.mappers.CharacterMapperService
import com.dashagy.data.repositories.MarvelCharacterRepositoryImpl
import com.dashagy.data.service.CharacterService
import com.dashagy.domain.repositories.MarvelCharacterRepository
import com.dashagy.domain.usecases.GetAllCharactersUseCase
import com.dashagy.domain.usecases.GetCharacterByIdUseCase
import com.dashagy.domain.usecases.GetCharactersByNameUseCase
import org.koin.dsl.module

val repositoriesModule = module {
    single { CharacterService() }
    single { CharacterDatabase() }
    single<MarvelCharacterRepository> { MarvelCharacterRepositoryImpl(get(), get()) }
}

val useCasesModule = module {
    single { GetCharacterByIdUseCase() }
    single { GetAllCharactersUseCase() }
    single { GetCharactersByNameUseCase() }
}

val mappersModule = module {
    single { CharacterMapperLocal() }
    single { CharacterMapperService() }
}