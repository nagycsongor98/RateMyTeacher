package com.example.ratemyteacher.ui.profile

import com.example.ratemyteacher.mvp.BasePresenter
import com.example.ratemyteacher.mvp.BaseView

/**
 * @author  Csongor Nagy
 * @since  10.05.2021
 */
class ProfileContract {
    interface View : BaseView {

    }

    abstract class Presenter(view: View?) : BasePresenter<View>(view) {

    }
}