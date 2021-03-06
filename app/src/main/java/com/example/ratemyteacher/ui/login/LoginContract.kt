package com.example.ratemyteacher.ui.login

import androidx.appcompat.app.AppCompatActivity
import com.example.ratemyteacher.mvp.BasePresenter
import com.example.ratemyteacher.mvp.BaseView

/**
 * @author  Csongor Nagy
 * @since  10.05.2021
 */
class LoginContract {
    interface View : BaseView {
        fun startMainActivity()
    }

    abstract class Presenter(view: View?) : BasePresenter<View>(view) {
        abstract fun checkLoginState()

        abstract fun login(email: String, password: String, activity: AppCompatActivity)
    }
}