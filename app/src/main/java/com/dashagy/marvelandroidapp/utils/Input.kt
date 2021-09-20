package com.dashagy.marvelandroidapp.utils

sealed class Input <out T : Any>{
    class StringInput <out T: Any> (val input: T) : Input<T>()
    class EmptyStringInput <out T: Any> (val input: T) : Input<T>()
    class NumberInput <out T: Any> (val input: T) : Input <T>()
}

fun evaluateInput(input: String) : Input<String> {
    return when (input.toIntOrNull()){
        null -> {
            when (input.isBlank()){
                true -> Input.EmptyStringInput(input)
                false -> Input.StringInput(input)
            }
        }
        else -> Input.NumberInput(input)
    }
}

/*when (evaluator){
    is Input.EmptyStringInput -> getAllCharacters( false) //mutableMainState.value = DataStatus.Successful(data = result.data)
    is Input.NumberInput -> getCharacterById(input.toInt(), false) //mutableMainState.value = DataStatus.Successful(data = listOf(result.data))
    is Input.StringInput -> getAllCharacters( false)
}*/