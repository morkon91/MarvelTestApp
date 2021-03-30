package com.app.marveltestapp.ui.fragment

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.marveltestapp.App
import com.app.marveltestapp.R
import com.app.marveltestapp.databinding.FragmentCharactersListBinding
import com.app.marveltestapp.di.components.CharactersUiComponent
import com.app.marveltestapp.ui.base.BaseFragment
import com.app.marveltestapp.ui.item.CharacterItem
import com.app.marveltestapp.viewModel.CharactersViewModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.scroll.EndlessRecyclerOnScrollListener
import com.mikepenz.fastadapter.ui.items.ProgressItem
import javax.inject.Inject

class CharactersListFragment :
    BaseFragment(R.layout.fragment_characters_list),
    Observer<CharactersViewModel.GetCompaniesResult> {

    companion object {
        fun createInstance() = CharactersListFragment()
    }

    private lateinit var binding: FragmentCharactersListBinding
    private var itemAdapter = ItemAdapter<CharacterItem>()
    private val footerAdapter = ItemAdapter<ProgressItem>()

    private var fastAdapter = FastAdapter.with(listOf(itemAdapter, footerAdapter))

    private lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var companiesViewModelFactory: ViewModelProvider.Factory

    private lateinit var charactersViewModel: CharactersViewModel

    private lateinit var paginator: EndlessRecyclerOnScrollListener

    private var flagShowNextList = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        charactersViewModel = ViewModelProvider(this, companiesViewModelFactory)
            .get(CharactersViewModel::class.java)

        charactersViewModel.initialize()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        binding = FragmentCharactersListBinding.bind(view)
        charactersViewModel.companiesLiveData.observe(viewLifecycleOwner, this)

        linearLayoutManager = LinearLayoutManager(
            this.context,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.companiesRecyclerView.layoutManager = linearLayoutManager

        binding.companiesRecyclerView.adapter = fastAdapter

        paginator = object :
            EndlessRecyclerOnScrollListener(footerAdapter) {
            override fun onLoadMore(currentPage: Int) {
                footerAdapter.clear()
                flagShowNextList = true
                charactersViewModel.updateCompaniesList(currentPage * 20)
            }

        }
        binding.companiesRecyclerView.addOnScrollListener(paginator)

        binding.swipeRefreshLayout.setOnRefreshListener {
            paginator.resetPageCount()
            itemAdapter.clear()
            charactersViewModel.updateCompaniesList(0)
        }

        fastAdapter.onClickListener = { _, _, item, _ ->
            if (item is CharacterItem) {

                //сделать открывашку деальной инфы через add
                getFlowNavigator()?.openFragment(
                    CharacterDetailsFragment.createInstance(item.characterResponse.id.toString()),
                    item.transitionInfoList
                )
            }
            false
        }
    }

    override fun initComponent() {
        App.componentStorage.getOrCreateComponent<CharactersUiComponent>().inject(this)
    }

    override fun releaseComponent() {
        App.componentStorage.releaseComponent<CharactersUiComponent>()
    }

    override fun onChanged(result: CharactersViewModel.GetCompaniesResult?) {
        when (result) {
            CharactersViewModel.GetCompaniesResult.Progress -> {
//                showToast("Загрузка...")
            }
            is CharactersViewModel.GetCompaniesResult.Success -> {

                var itemList = result.charactersList.map { companyBriefData ->
                    CharacterItem(companyBriefData)
                }

                if (flagShowNextList){
                    itemAdapter.add(itemList)
                    flagShowNextList = false
                }

                binding.swipeRefreshLayout.isRefreshing = false

                (view?.parent as? ViewGroup)?.doOnPreDraw {
                    startPostponedEnterTransition()
                }
                binding.progressFrameLayout.visibility = View.GONE
            }
            is CharactersViewModel.GetCompaniesResult.Error -> {
                showToast("${result.throwable.message ?: "Неизвестная ошибка"}")
                binding.swipeRefreshLayout.isRefreshing = false
                binding.progressFrameLayout.visibility = View.GONE
            }

        }
    }
}