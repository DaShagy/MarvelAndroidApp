package com.dashagy.marvelandroidapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dashagy.domain.entities.MarvelCharacter
import com.dashagy.domain.usecases.GetAllCharactersUseCase
import com.dashagy.domain.usecases.GetCharacterByIdUseCase
import com.dashagy.domain.util.ResultWrapper
import com.dashagy.marvelandroidapp.utils.DataStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterViewModel(
    val getCharacterById: GetCharacterByIdUseCase,
    val getAllCharacters: GetAllCharactersUseCase
    ) : ViewModel() {

    private var mutableMainState: MutableLiveData<DataStatus<List<MarvelCharacter>>> = MutableLiveData()
    val mainState: LiveData<DataStatus<List<MarvelCharacter>>>
        get() = mutableMainState

    fun onRemoteSearchClicked() = viewModelScope.launch {
        mutableMainState.value = DataStatus.Loading
        when (val result = withContext(Dispatchers.IO) { getAllCharacters(true) }) {
            is ResultWrapper.Failure -> {
                mutableMainState.value = DataStatus.Error(error = result.exception)
            }
            is ResultWrapper.Success -> {
                mutableMainState.value = DataStatus.Successful(data = result.data)
            }
        }
    }

    fun onLocalSearchClicked() = viewModelScope.launch {
        mutableMainState.value = DataStatus.Loading
        when (val result = withContext(Dispatchers.IO) { getAllCharacters( false) }) {
            is ResultWrapper.Failure -> {
                mutableMainState.value = DataStatus.Error(error = result.exception)
            }
            is ResultWrapper.Success -> {
                mutableMainState.value = DataStatus.Successful(data = result.data)
            }
        }
    }
}


