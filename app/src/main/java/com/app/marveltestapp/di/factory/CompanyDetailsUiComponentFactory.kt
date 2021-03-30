package com.app.marveltestapp.di.factory

import com.app.marveltestapp.di.components.CharacterDetailsUIComponent
import com.app.marveltestapp.di.components.DaggerCharacterDetailsUIComponent
import com.app.marveltestapp.di.modules.CharacterDetailsUiModule
import studio.inprogress.componentstorage.componentstorage.core.ComponentStorage
import studio.inprogress.componentstorage.componentstorage.core.factory.IComponentFactory

class CompanyDetailsUiComponentFactory : IComponentFactory<CharacterDetailsUIComponent> {

    override fun create(componentStorage: ComponentStorage): CharacterDetailsUIComponent {
        return DaggerCharacterDetailsUIComponent.builder()
            .characterDetailsUiModule(CharacterDetailsUiModule())
            .charactersComponent(componentStorage.getOrCreateComponent())
            .build()
    }

    override fun getName(): String = CharacterDetailsUIComponent::class.java.simpleName

    override fun isReleasable(): Boolean = true
}