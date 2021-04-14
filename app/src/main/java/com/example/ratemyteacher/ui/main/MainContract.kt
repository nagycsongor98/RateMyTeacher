package com.example.ratemyteacher.ui.main

import com.example.ratemyteacher.mvp.BasePresenter
import com.example.ratemyteacher.mvp.BaseView

/**
 * @author  Csongor Nagy
 * @since  14.04.2021
 */
class MainContract {
    interface View : BaseView {

    }

    abstract class Presenter(view: View?) : BasePresenter<View>(view) {

    }
}