package com.app.marveltestapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.marveltestapp.model.entity.CharactersDataResponse
import com.app.marveltestapp.model.interactor.ICharactersInteractor
import com.app.marveltestapp.utils.applySingleSchedulers
import io.reactivex.disposables.Disposable

class CharactersViewModel(private val charactersInteractor: ICharactersInteractor) : ViewModel() {

    val companiesLiveData = MutableLiveData<GetCompaniesResult>()

    private var companiesDisposable: Disposable? = null

    fun initialize() {
        updateCompaniesList(0)
    }

    fun updateCompaniesList(offset: Int) {

        companiesDisposable?.dispose()
        companiesDisposable = charactersInteractor.getAllCharacters(offset)
            .applySingleSchedulers()
            .doOnSubscribe {
                companiesLiveData.value = GetCompaniesResult.Progress
            }
            .subscribe(
                { response ->
                        companiesLiveData.value = GetCompaniesResult.Success(
                            response.data.results
                        )
                },
                { throwable ->
                    companiesLiveData.value = GetCompaniesResult.Error(throwable)
                }
            )
    }


    override fun onCleared() {
        super.onCleared()
        companiesDisposable?.dispose()
    }

    sealed class GetCompaniesResult {
        class Success(val charactersList: List<CharactersDataResponse.Data.Result>) : GetCompaniesResult()
        class Error(val throwable: Throwable) : GetCompaniesResult()
        object Progress : GetCompaniesResult()
    }
}