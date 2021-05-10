package com.example.ratemyteacher.ui.main

import android.content.Intent
import android.os.Bundle
import com.example.ratemyteacher.R
import com.example.ratemyteacher.databinding.ActivityMainBinding
import com.example.ratemyteacher.ui.base.BaseActivity
import com.example.ratemyteacher.ui.rateteacher.RateTeacherActivity
import com.example.ratemyteacher.ui.teacherslist.TeachersListActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.View{

    override val presenter: MainContract.Presenter? by inject { parametersOf(this) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.openRateButton.setOnClickListener {
            startActivity(Intent(this, RateTeacherActivity::class.java))
        }

        binding.openTeachersListButton.setOnClickListener {
            startActivity(Intent(this, TeachersListActivity::class.java))
        }
    }
}