package com.example.ratemyteacher.ui.base

import androidx.fragment.app.Fragment
import com.example.ratemyteacher.mvp.BasePresenter
import com.example.ratemyteacher.mvp.BaseView

/**
 * @author  Csongor Nagy
 * @since  14.04.2021
 */

abstract class BaseFragment<T : BasePresenter<*>> : Fragment(), BaseView {

    protected abstract val presenter: T?

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDetachView()
    }

}
