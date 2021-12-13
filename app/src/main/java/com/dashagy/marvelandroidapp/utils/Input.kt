package com.dashagy.marvelandroidapp.utils

sealed class Input <out String>{
    class StringInput <out String> : Input<String>()
    class EmptyStringInput <out String> : Input<String>()
    class NumberInput <out String> : Input <String>()
}

fun evaluateInput(input: String) : Input<String> {
    return when (input.toIntOrNull()){
        null -> {
            when (input.isBlank()){
                true -> Input.EmptyStringInput()
                false -> Input.StringInput()
            }
        }
        else -> Input.NumberInput()
    }
}