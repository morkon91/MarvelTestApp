package com.app.marveltestapp.di.factory

import com.app.marveltestapp.di.components.CharactersUiComponent
import com.app.marveltestapp.di.components.DaggerCharactersUiComponent
import com.app.marveltestapp.di.modules.CharactersUiModule
import studio.inprogress.componentstorage.componentstorage.core.ComponentStorage
import studio.inprogress.componentstorage.componentstorage.core.factory.IComponentFactory

class CharactersUiComponentFactory : IComponentFactory<CharactersUiComponent> {

    override fun create(componentStorage: ComponentStorage): CharactersUiComponent {
        return DaggerCharactersUiComponent.builder()
            .charactersUiModule(CharactersUiModule())
            .charactersComponent(componentStorage.getOrCreateComponent())
            .build()
    }

    override fun getName(): String = CharactersUiComponent::class.java.simpleName

    override fun isReleasable(): Boolean = true
}