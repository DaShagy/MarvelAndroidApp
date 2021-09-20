package com.dashagy.marvelandroidapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dashagy.domain.entities.MarvelCharacter
import com.dashagy.domain.usecases.GetAllCharactersUseCase
import com.dashagy.domain.usecases.GetCharacterByIdUseCase
import com.dashagy.domain.usecases.GetCharactersByNameUseCase
import com.dashagy.domain.util.ResultWrapper
import com.dashagy.marvelandroidapp.utils.DataStatus
import com.dashagy.marvelandroidapp.utils.Input
import com.dashagy.marvelandroidapp.utils.evaluateInput
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterViewModel(
    val getCharacterById: GetCharacterByIdUseCase,
    val getAllCharacters: GetAllCharactersUseCase,
    val getCharactersByName: GetCharactersByNameUseCase
    ) : ViewModel() {

    private var mutableMainState: MutableLiveData<DataStatus<List<MarvelCharacter>>> = MutableLiveData()
    val mainState: LiveData<DataStatus<List<MarvelCharacter>>>
        get() {
            return mutableMainState
        }

    fun onRemoteSearchClicked(input: String) = viewModelScope.launch {
        mutableMainState.value = DataStatus.Loading
        when (val result = withContext(Dispatchers.IO) {
            evaluateInputAndRetrieveCharacters(input, true)
        }) {
            is ResultWrapper.Failure -> {
                mutableMainState.value = DataStatus.Error(error = result.exception)
            }
            is ResultWrapper.Success -> {
                mutableMainState.value = DataStatus.Successful(data = result.data)
            }
        }
    }

    fun onLocalSearchClicked(input: String) = viewModelScope.launch {
        mutableMainState.value = DataStatus.Loading
        when (val result = withContext(Dispatchers.IO) {
            evaluateInputAndRetrieveCharacters(input, false)
            }) {
            is ResultWrapper.Failure -> {
                mutableMainState.value = DataStatus.Error(error = result.exception)
            }
            is ResultWrapper.Success -> {
                mutableMainState.value = DataStatus.Successful(data = result.data)
            }
        }
    }

    private fun evaluateInputAndRetrieveCharacters (input: String, getFromRemote: Boolean) = run {
        when (evaluateInput(input)){
            is Input.EmptyStringInput -> getAllCharacters( getFromRemote)
            is Input.NumberInput -> getCharacterById(input.toInt(), getFromRemote)
            is Input.StringInput -> getCharactersByName(input, getFromRemote)
        }
    }
}


