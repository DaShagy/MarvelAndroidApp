package com.dashagy.domain.util

import java.lang.Exception

sealed class ResultWrapper<out T : Any> {
    class Success<out T : Any>(val data: T) : ResultWrapper<T>()
    class Failure(val exception: Exception) : ResultWrapper<Nothing>()
}