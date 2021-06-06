package com.example.ratemyteacher.ui.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ratemyteacher.mvp.BasePresenter
import com.example.ratemyteacher.mvp.BaseView

/**
 * @author  Csongor Nagy
 * @since  14.04.2021
 */

abstract class BaseActivity<T : BasePresenter<*>> : AppCompatActivity(), BaseView {

    protected abstract val presenter: T?

    override fun showText(text: String) {
        Toast.makeText(baseContext, text, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDetachView()
    }
}