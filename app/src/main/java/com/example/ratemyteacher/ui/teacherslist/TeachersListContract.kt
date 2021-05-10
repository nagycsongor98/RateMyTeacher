package com.example.ratemyteacher.ui.teacherslist

import com.example.ratemyteacher.mvp.BasePresenter
import com.example.ratemyteacher.mvp.BaseView

/**
 * @author  Csongor Nagy
 * @since  10.05.2021
 */
class TeachersListContract {
    interface View : BaseView {

    }

    abstract class Presenter(view: View?) : BasePresenter<View>(view) {

    }
}