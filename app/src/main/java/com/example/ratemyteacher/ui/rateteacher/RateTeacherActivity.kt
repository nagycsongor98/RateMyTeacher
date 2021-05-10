package com.example.ratemyteacher.ui.rateteacher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ratemyteacher.R
import com.example.ratemyteacher.databinding.ActivityMainBinding
import com.example.ratemyteacher.databinding.ActivityReatTeacherBinding
import com.example.ratemyteacher.ui.base.BaseActivity
import com.example.ratemyteacher.ui.main.MainContract
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class RateTeacherActivity : BaseActivity<RateTeacherContract.Presenter>(), RateTeacherContract.View{

    override val presenter: RateTeacherContract.Presenter? by inject { parametersOf(this) }
    private lateinit var binding: ActivityReatTeacherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReatTeacherBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}