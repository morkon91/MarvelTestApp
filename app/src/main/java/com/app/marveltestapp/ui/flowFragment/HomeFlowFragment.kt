package com.app.marveltestapp.ui.flowFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.app.marveltestapp.App
import com.app.marveltestapp.R
import com.app.marveltestapp.databinding.FragmentFlowHomeBinding
import com.app.marveltestapp.di.components.CharactersComponent
import com.app.marveltestapp.ui.TransitionInfo
import com.app.marveltestapp.ui.fragment.CharactersListFragment
import com.app.marveltestapp.ui.base.BaseFlowFragment
import com.app.marveltestapp.ui.fragment.CharacterDetailsFragment

class HomeFlowFragment : BaseFlowFragment(R.layout.fragment_flow_home) {

    companion object {
        fun createInstance() = HomeFlowFragment()
    }

    private lateinit var binding: FragmentFlowHomeBinding
    private var startFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            startFragment = CharactersListFragment.createInstance()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFlowHomeBinding.bind(view)

        startFragment?.let {
            openFragment(it)
            startFragment = null
        }
    }


    override fun initComponent() {
        App.componentStorage.getOrCreateComponent<CharactersComponent>()
    }

    override fun releaseComponent() {
        App.componentStorage.releaseComponent<CharactersComponent>()
    }

    override fun openFragment(fragment: Fragment, transitionInfoList: List<TransitionInfo>?) {
        when (fragment) {
            is CharactersListFragment -> replaceFragment(fragment, false)
            is CharacterDetailsFragment -> replaceFragment(fragment, true, transitionInfoList)
        }
    }

}