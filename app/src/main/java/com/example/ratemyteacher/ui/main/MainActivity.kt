package com.example.ratemyteacher.ui.main

import android.os.Bundle
import com.example.ratemyteacher.R
import com.example.ratemyteacher.ui.base.BaseActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.View{

    override val presenter: MainContract.Presenter? by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}