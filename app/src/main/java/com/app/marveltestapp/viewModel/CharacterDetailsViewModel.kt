package com.app.marveltestapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.marveltestapp.model.entity.CharacterData
import com.app.marveltestapp.model.entity.CharactersDataResponse
import com.app.marveltestapp.model.interactor.ICharactersInteractor
import com.app.marveltestapp.utils.applySingleSchedulers
import io.reactivex.disposables.Disposable

class CharacterDetailsViewModel(private val charactersInteractor: ICharactersInteractor) : ViewModel() {

    val companyDetailsLiveData = MutableLiveData<GetCompanyDetailsResult>()

    private var companyDisposable: Disposable? = null

    private lateinit var companyId: String

    fun initialize(companyId: String) {
        this.companyId = companyId
        getCompanyDetails()
    }

    fun getCompanyDetails() {
        companyDisposable = charactersInteractor.getCharacterDetailsById(companyId)
            .applySingleSchedulers()
            .doOnSubscribe {
                companyDetailsLiveData.value = GetCompanyDetailsResult.Progress
            }.subscribe({ result ->
                companyDetailsLiveData.value = GetCompanyDetailsResult.Success(
                result.data.results
                )
            }, {
                companyDetailsLiveData.value = GetCompanyDetailsResult.Error(it)
            })
    }

    override fun onCleared() {
        super.onCleared()
        companyDisposable?.dispose()
    }

    sealed class GetCompanyDetailsResult {
        class Success(val characterData: List<CharactersDataResponse.Data.Result>) : GetCompanyDetailsResult()
        class Error(val throwable: Throwable) : GetCompanyDetailsResult()
        object Progress : GetCompanyDetailsResult()
    }
}