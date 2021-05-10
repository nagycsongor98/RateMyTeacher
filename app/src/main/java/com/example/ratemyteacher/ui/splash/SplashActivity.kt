package com.example.ratemyteacher.ui.splash

import android.os.Bundle
import com.example.ratemyteacher.R
import com.example.ratemyteacher.ui.base.BaseActivity
import org.koin.android.ext.android.inject

class SplashActivity : BaseActivity<SplashContract.Presenter>(), SplashContract.View{

    override val presenter: SplashContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}