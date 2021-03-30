package com.app.marveltestapp.di.components

import com.app.marveltestapp.di.modules.CharactersUiModule
import com.app.marveltestapp.di.scopes.CharactersUiScope
import com.app.marveltestapp.ui.fragment.CharactersListFragment
import dagger.Component

@Component(dependencies = [CharactersComponent::class], modules = [CharactersUiModule::class])
@CharactersUiScope
interface CharactersUiComponent {

    fun inject(fragment: CharactersListFragment)
}