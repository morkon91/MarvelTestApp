package com.app.marveltestapp.di.modules

import com.app.marveltestapp.di.scopes.CharactersScope
import com.app.marveltestapp.model.dataSourse.ICharactersApi
import com.app.marveltestapp.model.interactor.ICharactersInteractor
import com.app.marveltestapp.model.interactor.impl.CharactersInteractor
import com.app.marveltestapp.model.repository.ICharacterRepository
import com.app.marveltestapp.model.repository.impl.CharacterRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CharactersModule {

    @Provides
    @CharactersScope
    fun provideCompaniesInteractor(repository: ICharacterRepository): ICharactersInteractor {
        return CharactersInteractor(repository)
    }

    @Provides
    @CharactersScope
    fun provideCompaniesRepository(charactersApi: ICharactersApi): ICharacterRepository {
        return CharacterRepository(charactersApi)
    }

    @Provides
    @CharactersScope
    fun provideCompaniesApi(retrofit: Retrofit): ICharactersApi {
        return retrofit.create(ICharactersApi::class.java)
    }
}