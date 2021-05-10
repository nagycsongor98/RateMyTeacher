package com.example.ratemyteacher.ui.teacherslist

import org.koin.core.KoinComponent

/**
 * @author  Csongor Nagy
 * @since  10.05.2021
 */
class TeachersListPresenter(view: TeachersListContract.View) : TeachersListContract.Presenter(view),
    KoinComponent {
}