package com.app.marveltestapp.di.modules

import androidx.lifecycle.ViewModelProvider
import com.app.marveltestapp.di.scopes.CharactersUiScope
import com.app.marveltestapp.model.interactor.ICharactersInteractor
import com.app.marveltestapp.viewModel.ViewModelFactory.CharactersViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class CharactersUiModule {

    @Provides
    @CharactersUiScope
    fun provideCompaniesViewModelFactory(
        interactor: ICharactersInteractor
    ): ViewModelProvider.Factory {

        return CharactersViewModelFactory(interactor)
    }
}