package com.app.marveltestapp.ui.base

import android.view.View
import androidx.fragment.app.Fragment
import com.app.marveltestapp.ui.TransitionInfo

/**
* Интерфейс нафигации внутри [BaseFlowFragment] фрагмента
*/
interface IFlowNavigator {

    fun openFragment(fragment: Fragment, transitionInfoList: List<TransitionInfo>? = null)

    fun onBackPressed()
}