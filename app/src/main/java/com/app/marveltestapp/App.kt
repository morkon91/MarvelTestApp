package com.app.marveltestapp

import android.app.Application
import android.util.Log
import com.app.marveltestapp.di.factory.CharactersComponentFactory
import com.app.marveltestapp.di.factory.CharactersUiComponentFactory
import com.app.marveltestapp.di.factory.CompanyDetailsUiComponentFactory
import com.app.marveltestapp.di.factory.CoreComponentFactory
import studio.inprogress.componentstorage.componentstorage.core.ComponentStorage

class App : Application() {

    companion object {
        lateinit var componentStorage: ComponentStorage
    }

    override fun onCreate() {
        super.onCreate()
        initComponentStorage()
    }

    private fun initComponentStorage() {
        componentStorage = ComponentStorage().apply {
            registerComponentFactory(
                CoreComponentFactory(this@App),
                CharactersComponentFactory(),
                CharactersUiComponentFactory(),
                CompanyDetailsUiComponentFactory()
            )
            withLogger {
                Log.d("ComponentStorage", it)
            }
        }
    }
}