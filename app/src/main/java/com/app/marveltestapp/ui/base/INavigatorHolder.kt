package com.app.marveltestapp.ui.base

/**
 * Интерфейс холдера навигации по [BaseFlowFragment]
 */
interface INavigatorHolder {

    /**
     * Возвращает интерфейс навигации по [BaseFlowFragment]
     */
    fun getNavigator(): INavigator
}