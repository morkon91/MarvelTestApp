package com.app.marveltestapp.di.components

import android.content.Context
import com.app.marveltestapp.di.modules.CharactersModule
import com.app.marveltestapp.di.scopes.CharactersScope
import com.app.marveltestapp.model.interactor.ICharactersInteractor
import com.app.marveltestapp.model.repository.ICharacterRepository
import dagger.Component


@Component(dependencies = [CoreComponent::class], modules = [CharactersModule::class])
@CharactersScope
interface CharactersComponent {

    fun provideContext(): Context
    fun provideCharactersInteractor(): ICharactersInteractor
    fun provideCharacterRepository(): ICharacterRepository

}