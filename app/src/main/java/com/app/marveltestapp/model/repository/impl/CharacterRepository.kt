package com.app.marveltestapp.model.repository.impl

import com.app.marveltestapp.model.dataSourse.ICharactersApi
import com.app.marveltestapp.model.entity.CharactersDataResponse
import com.app.marveltestapp.model.entity.CharacterData
import com.app.marveltestapp.model.repository.ICharacterRepository
import io.reactivex.Single

class CharacterRepository(private val charactersApi: ICharactersApi) : ICharacterRepository {
    override fun getAllCharacters(offset: Int): Single<CharactersDataResponse> {
        return charactersApi.fetchAllCharacters(offset)
    }

    override fun getCharacterDetailsById(characterId: String): Single<CharactersDataResponse> {
        return charactersApi.fetchCharactersDetailsById(characterId)
    }
}