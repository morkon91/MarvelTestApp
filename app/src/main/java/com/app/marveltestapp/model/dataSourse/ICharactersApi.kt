package com.app.marveltestapp.model.dataSourse

import com.app.marveltestapp.model.entity.CharactersDataResponse
import com.app.marveltestapp.model.entity.CharacterData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ICharactersApi {

    @GET("/v1/public/characters")
    fun fetchAllCharacters(@Query("offset") offset: Int): Single<CharactersDataResponse>

    @GET("/v1/public/characters/{character_id}")
    fun fetchCharactersDetailsById(@Path("character_id") characterId: String): Single<CharactersDataResponse>
}