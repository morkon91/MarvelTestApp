package com.app.marveltestapp.di.modules

import androidx.lifecycle.ViewModelProvider
import com.app.marveltestapp.di.scopes.CharacterDetailsUiScope
import com.app.marveltestapp.model.interactor.ICharactersInteractor
import com.app.marveltestapp.viewModel.ViewModelFactory.CharactersViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class CharacterDetailsUiModule {

    @Provides
    @CharacterDetailsUiScope
    fun provideCompaniesViewModelFactory(
        interactor: ICharactersInteractor
    ): ViewModelProvider.Factory {

        return CharactersViewModelFactory(interactor)
    }
}