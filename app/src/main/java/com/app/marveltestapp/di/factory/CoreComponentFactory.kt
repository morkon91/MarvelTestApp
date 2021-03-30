package com.app.marveltestapp.di.factory

import android.content.Context
import com.app.marveltestapp.di.components.CoreComponent
import com.app.marveltestapp.di.components.DaggerCoreComponent
import com.app.marveltestapp.di.modules.AppModule
import com.app.marveltestapp.di.modules.NetworkModule
import studio.inprogress.componentstorage.componentstorage.core.ComponentStorage
import studio.inprogress.componentstorage.componentstorage.core.factory.IComponentFactory

class CoreComponentFactory(private val appContext: Context) : IComponentFactory<CoreComponent> {
    override fun create(componentStorage: ComponentStorage): CoreComponent {
        return DaggerCoreComponent.builder()
            .appModule(AppModule(appContext))
            .networkModule(NetworkModule())
            .build()
    }

    override fun getName(): String =CoreComponent::class.java.simpleName

    override fun isReleasable(): Boolean = false
}