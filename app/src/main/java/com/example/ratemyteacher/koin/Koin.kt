package com.example.ratemyteacher.koin

import com.example.ratemyteacher.ui.main.MainContract
import com.example.ratemyteacher.ui.main.MainPresenter
import org.koin.dsl.module

/**
 * @author  Csongor Nagy
 * @since  14.04.2021
 */

val uiModule = module {
    factory { (view: MainContract.View) -> MainPresenter(view) as MainContract.Presenter }
}