package com.example.ratemyteacher.ui.reviewslist

import com.example.ratemyteacher.mvp.BasePresenter
import com.example.ratemyteacher.mvp.BaseView
import com.example.ratemyteacher.ui.rateteacher.Review

/**
 * @author  Csongor Nagy
 * @since  10.05.2021
 */
class ReviewsListContract {
    interface View : BaseView {

        fun getSelectedDepartment(): String

        fun setUpTeacherSpinner(teachers: ArrayList<String>)

        fun setupDepartmentSpinner(categories: ArrayList<String>)

        fun showReviews(reviews: ArrayList<Review>)

    }

    abstract class Presenter(view: View?) : BasePresenter<View>(view) {
        abstract fun getTeachers()

        abstract fun getCategories()

        abstract fun getReviews(teacher: String)
    }
}