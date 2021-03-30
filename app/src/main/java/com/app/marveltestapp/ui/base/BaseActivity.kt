package com.app.marveltestapp.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.marveltestapp.App

/**
 * Базовая активити
 */
abstract class BaseActivity : AppCompatActivity() {

    val componentStorage = App.componentStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        initComponents()
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        if (isFinishing) {
            releaseComponent()
        }
        super.onDestroy()
    }

    /**
     * Инициализация компонентов
     */
    abstract fun initComponents()

    /**
     * Сигнал о том, что кможно послать запрос на уничтожение компонента
     */
    abstract fun releaseComponent()
}