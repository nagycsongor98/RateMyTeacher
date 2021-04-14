package com.example.ratemyteacher.ui.main

import org.koin.core.KoinComponent

/**
 * @author  Csongor Nagy
 * @since  14.04.2021
 */
class MainPresenter(view: MainContract.View): MainContract.Presenter(view), KoinComponent {
}