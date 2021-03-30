package com.app.marveltestapp.model.repository

import com.app.marveltestapp.model.entity.CharactersDataResponse
import com.app.marveltestapp.model.entity.CharacterData
import io.reactivex.Single

interface ICharacterRepository {

    fun getAllCharacters(offset: Int): Single<CharactersDataResponse>

    fun getCharacterDetailsById(characterId: String): Single<CharactersDataResponse>
}