package com.app.marveltestapp.di.components

import com.app.marveltestapp.di.modules.CharacterDetailsUiModule
import com.app.marveltestapp.di.scopes.CharacterDetailsUiScope
import com.app.marveltestapp.ui.fragment.CharacterDetailsFragment
import dagger.Component

@Component(
    dependencies = [CharactersComponent::class],
    modules = [CharacterDetailsUiModule::class]
)
@CharacterDetailsUiScope
interface CharacterDetailsUIComponent {
    fun inject(fragment: CharacterDetailsFragment)
}