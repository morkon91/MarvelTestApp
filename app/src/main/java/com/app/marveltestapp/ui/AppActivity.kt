package com.app.marveltestapp.ui

import android.os.Bundle
import com.app.marveltestapp.R
import com.app.marveltestapp.ui.base.BaseActivity
import com.app.marveltestapp.ui.base.INavigator
import com.app.marveltestapp.ui.base.INavigatorHolder

class AppActivity : BaseActivity(), INavigatorHolder {

    private lateinit var appNavigator: INavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appNavigator = AppNavigator(supportFragmentManager)
        appNavigator.openHomeFlowFragment()

    }

    override fun initComponents() {
        // no op
    }

    override fun releaseComponent() {
        // no op
    }

    override fun getNavigator(): INavigator {
        return appNavigator
    }
}