package com.example.ratemyteacher

import android.app.Application
import com.example.ratemyteacher.koin.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin

/**
 * @author  Csongor Nagy
 * @since  14.04.2021
 */
class Application: Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(
                listOf(
                    uiModule
                )
            )
        }
    }
}