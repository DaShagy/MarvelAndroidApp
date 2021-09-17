package com.dashagy.data.service.api

import com.dashagy.data.database.response.DatabaseResponse
import com.dashagy.data.service.CharacterResponse
import com.dashagy.data.service.response.MarvelBaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MarvelApi {
    @GET("/v1/public/characters/{characterId}")
    fun getCharacterById(@Path("characterId")id: Int): Call<MarvelBaseResponse<DatabaseResponse<ArrayList<CharacterResponse>>>>

    @GET("/v1/public/characters")
    fun getAllCharacters(@QueryMap filter: HashMap<String, String>): Call<MarvelBaseResponse<DatabaseResponse<ArrayList<CharacterResponse>>>>
}
