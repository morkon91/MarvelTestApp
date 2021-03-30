package com.app.marveltestapp.viewModel.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.marveltestapp.model.interactor.ICharactersInteractor
import com.app.marveltestapp.viewModel.CharactersViewModel
import com.app.marveltestapp.viewModel.CharacterDetailsViewModel

@Suppress("UNCHECKED_CAST")
class CharactersViewModelFactory(
    private val interactor: ICharactersInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(CharactersViewModel::class.java) ->
                CharactersViewModel(interactor) as T
            modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java) ->
                CharacterDetailsViewModel(interactor) as T
            else -> throw IllegalArgumentException()
        }
    }
}