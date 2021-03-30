package com.app.marveltestapp.ui.base

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.app.marveltestapp.App

/**
 * Базовый фрагмент. Используется внутри [BaseFlowFragment]
 */
abstract class BaseFragment : Fragment {

    constructor() : super()

    constructor(@LayoutRes layoutResId: Int) : super(layoutResId)

    val componentStorage = App.componentStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        initComponent()
        super.onCreate(savedInstanceState)
    }

    abstract fun initComponent()

    override fun onDestroy() {
        releaseComponent()
        super.onDestroy()
    }

    abstract fun releaseComponent()

    protected fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(@StringRes resId: Int) {
        Toast.makeText(requireContext(), resId, Toast.LENGTH_SHORT).show()
    }

    protected fun hideKeyboard() {
        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = requireActivity().currentFocus
        if (view != null) {
            inputMethodManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    fun getFlowNavigator(): IFlowNavigator? {
        return parentFragment as? IFlowNavigator
    }

    fun getNavigator(): INavigator {
        return (requireActivity() as INavigatorHolder).getNavigator()
    }
}