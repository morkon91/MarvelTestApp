package com.app.marveltestapp.ui.base

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.app.marveltestapp.R
import com.app.marveltestapp.ui.TransitionInfo

/**
 * Базовый фрагмент для организации логически связанных экранов
 */
abstract class BaseFlowFragment : Fragment, IFlowNavigator {

    constructor() : super()

    constructor(@LayoutRes layoutResId: Int) : super(layoutResId)

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initComponent()
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    abstract fun initComponent()

    override fun onDestroy() {
        releaseComponent()
        super.onDestroy()
    }

    abstract fun releaseComponent()

    fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun showToast(@StringRes resId: Int) {
        Toast.makeText(requireContext(), resId, Toast.LENGTH_SHORT).show()
    }

    fun hideKeyboard() {
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = requireActivity().currentFocus
        if (view != null) {
            inputMethodManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    fun replaceFragment(
        fragment: Fragment, addToBackStack: Boolean,
        transitionInfoList: List<TransitionInfo>? = null
    ) {

        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment, fragment::class.java.simpleName)
        if (addToBackStack) {
            transaction.addToBackStack(fragment::class.java.simpleName)
        }
        transitionInfoList?.forEach {
            transaction.setReorderingAllowed(true)
            transaction.addSharedElement(it.view, it.transitionName)
        }
        transaction.commit()
    }

    fun addFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainer, fragment, fragment::class.java.simpleName)
        if (addToBackStack) {
            transaction.addToBackStack(fragment::class.java.simpleName)
            transaction.setPrimaryNavigationFragment(this)
        }

        transaction.commit()
    }

    fun getNavigator(): INavigator {
        return (requireActivity() as INavigatorHolder).getNavigator()
    }

    override fun onBackPressed() {
        when {
            childFragmentManager.backStackEntryCount > 0 -> {
                childFragmentManager.popBackStack()
            }
            requireFragmentManager().backStackEntryCount > 0 -> {
                requireFragmentManager().popBackStack()
            }
            else -> {
                onBackPressedCallback.isEnabled = false
                requireActivity().onBackPressed()
            }
        }
    }
}