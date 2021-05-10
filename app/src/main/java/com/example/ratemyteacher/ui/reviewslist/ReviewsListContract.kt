package com.example.ratemyteacher.ui.reviewslist

import com.example.ratemyteacher.mvp.BasePresenter
import com.example.ratemyteacher.mvp.BaseView

/**
 * @author  Csongor Nagy
 * @since  10.05.2021
 */
class ReviewsListContract {
    interface View : BaseView {

    }

    abstract class Presenter(view: View?) : BasePresenter<View>(view) {

    }
}