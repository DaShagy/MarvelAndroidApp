package com.dashagy.marvelandroidapp.utils

sealed class Input <out T : Any>{
    class StringInput <out T: Any> (val input: T) : Input<T>()
    class EmptyStringInput <out T: Any> (val input: T) : Input<T>()
    class NumberInput <out T: Any> (val input: T) : Input <T>()
}

fun evaluateInput(input: String) : Input<String> {
    return when (input.toIntOrNull()){
        null -> {
            when (input.isNullOrBlank()){
                true -> Input.EmptyStringInput(input)
                false -> Input.StringInput(input)
            }
        }
        else -> Input.NumberInput(input)
    }
}