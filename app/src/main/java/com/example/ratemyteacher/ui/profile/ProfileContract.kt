package com.example.ratemyteacher.ui.profile

import com.example.ratemyteacher.mvp.BasePresenter
import com.example.ratemyteacher.mvp.BaseView

/**
 * @author  Csongor Nagy
 * @since  10.05.2021
 */
class ProfileContract {
    interface View : BaseView {
        fun setupProfileCategories(categories: Array<String>)

        fun setupProfileSelectedCategory(index: Int)

        fun setupProfileEmail(email: String)

        fun setupProfileName(name: String)
    }

    abstract class Presenter(view: View?) : BasePresenter<View>(view) {
        abstract fun saveUser(profileNameText: String, categorySpinner:String) 
        
        abstract fun loadDataFromFirebase(categories: Array<String>)
    }
}