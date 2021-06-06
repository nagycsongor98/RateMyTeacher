package com.example.ratemyteacher.ui.splash

import com.example.ratemyteacher.mvp.BasePresenter
import com.example.ratemyteacher.mvp.BaseView

/**
 * @author  Csongor Nagy
 * @since  10.05.2021
 */
class SplashContract {
    interface View : BaseView {
        fun startApplication()
    }

    abstract class Presenter(view: View?) : BasePresenter<View>(view) {
        abstract fun startCounter()
    }
}