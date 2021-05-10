package com.example.ratemyteacher.koin

import com.example.ratemyteacher.ui.login.LoginContract
import com.example.ratemyteacher.ui.login.LoginPresenter
import com.example.ratemyteacher.ui.main.MainContract
import com.example.ratemyteacher.ui.main.MainPresenter
import com.example.ratemyteacher.ui.splash.SplashContract
import com.example.ratemyteacher.ui.splash.SplashPresenter
import org.koin.dsl.module

/**
 * @author  Csongor Nagy
 * @since  14.04.2021
 */

val uiModule = module {
    factory { (view: SplashContract.View) -> SplashPresenter(view) as SplashContract.Presenter }
    factory { (view: LoginContract.View) -> LoginPresenter(view) as LoginContract.Presenter }
    factory { (view: MainContract.View) -> MainPresenter(view) as MainContract.Presenter }
}