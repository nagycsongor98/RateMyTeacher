package com.example.ratemyteacher.ui.signup

import androidx.appcompat.app.AppCompatActivity
import com.example.ratemyteacher.mvp.BasePresenter
import com.example.ratemyteacher.mvp.BaseView

/**
 * @author  Csongor Nagy
 * @since  06.06.2021
 */
class SignUpContract {
    interface View : BaseView {
        fun startMainActivity()
    }

    abstract class Presenter(view: View?) : BasePresenter<View>(view) {
        abstract fun signUp(email: String, password: String, reenteredPassword: String, activity: AppCompatActivity)
    }
}