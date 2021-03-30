package com.app.marveltestapp.model.interactor.impl

import com.app.marveltestapp.model.entity.CharactersDataResponse
import com.app.marveltestapp.model.entity.CharacterData
import com.app.marveltestapp.model.interactor.ICharactersInteractor
import com.app.marveltestapp.model.repository.ICharacterRepository
import io.reactivex.Single

class CharactersInteractor(private val repository: ICharacterRepository) : ICharactersInteractor {

    override fun getAllCharacters(offset: Int): Single<CharactersDataResponse> {
        return repository.getAllCharacters(offset)
    }

    override fun getCharacterDetailsById(companyId: String): Single<CharactersDataResponse> {
        return repository.getCharacterDetailsById(companyId)
    }


}