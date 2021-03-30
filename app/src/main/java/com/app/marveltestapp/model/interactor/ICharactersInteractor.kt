package com.app.marveltestapp.model.interactor

import com.app.marveltestapp.model.entity.CharactersDataResponse
import com.app.marveltestapp.model.entity.CharacterData
import io.reactivex.Single

interface ICharactersInteractor {

    fun getAllCharacters(offset: Int): Single<CharactersDataResponse>

    fun getCharacterDetailsById(companyId: String): Single<CharactersDataResponse>
}