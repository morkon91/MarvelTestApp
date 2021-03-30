package com.app.marveltestapp.utils

import android.view.View

fun View.resolveVisibility(isVisible: Boolean, invisibleState: Int = View.GONE) {
    visibility = if (isVisible) View.VISIBLE else invisibleState
}