package com.dashagy.data.service.response

data class MarvelBaseResponse<T> (
    var code: Int,
    var status: String,
    var data: T?
)
