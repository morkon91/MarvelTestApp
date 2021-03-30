package com.app.marveltestapp.ui

import androidx.fragment.app.FragmentManager
import com.app.marveltestapp.ui.base.INavigator
import com.app.marveltestapp.ui.flowFragment.HomeFlowFragment

class AppNavigator(private val fragmentManager: FragmentManager) : INavigator {
    override fun openHomeFlowFragment() {
        fragmentManager.beginTransaction()
            .replace((android.R.id.content), HomeFlowFragment.createInstance())
            .commit()
    }


}