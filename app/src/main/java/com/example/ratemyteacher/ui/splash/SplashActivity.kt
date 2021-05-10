package com.example.ratemyteacher.ui.splash

import android.content.Intent
import android.os.Bundle
import com.example.ratemyteacher.R
import com.example.ratemyteacher.databinding.ActivitySplashBinding
import com.example.ratemyteacher.ui.base.BaseActivity
import com.example.ratemyteacher.ui.login.LoginActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SplashActivity : BaseActivity<SplashContract.Presenter>(), SplashContract.View{

    override val presenter: SplashContract.Presenter by inject{ parametersOf(this) }
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}