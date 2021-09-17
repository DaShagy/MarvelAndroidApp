package com.dashagy.marvelandroidapp.di

import com.dashagy.marvelandroidapp.viewmodels.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { CharacterViewModel(get(), get()) }
}