package com.app.marveltestapp.di.factory

import com.app.marveltestapp.di.components.CharactersComponent
import com.app.marveltestapp.di.components.DaggerCharactersComponent
import com.app.marveltestapp.di.modules.CharactersModule
import studio.inprogress.componentstorage.componentstorage.core.ComponentStorage
import studio.inprogress.componentstorage.componentstorage.core.factory.IComponentFactory

class CharactersComponentFactory : IComponentFactory<CharactersComponent> {
    override fun create(componentStorage: ComponentStorage): CharactersComponent {
        return DaggerCharactersComponent.builder()
            .charactersModule(CharactersModule())
            .coreComponent(componentStorage.getOrCreateComponent())
            .build()
    }

    override fun getName(): String = CharactersComponent::class.java.simpleName

    override fun isReleasable(): Boolean = true
}