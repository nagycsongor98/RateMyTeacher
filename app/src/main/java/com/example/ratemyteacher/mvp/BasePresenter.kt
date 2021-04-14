package com.example.ratemyteacher.mvp


/**
 * @author  Csongor Nagy
 * @since  14.04.2021
 */

abstract class BasePresenter<T : BaseView>(var view: T?) {

    fun onDetachView() {
        view = null
    }

}
