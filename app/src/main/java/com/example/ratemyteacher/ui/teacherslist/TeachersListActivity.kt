package com.example.ratemyteacher.ui.teacherslist

import android.os.Bundle
import com.example.ratemyteacher.databinding.ActivityTeachersListBinding
import com.example.ratemyteacher.ui.base.BaseActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class TeachersListActivity : BaseActivity<TeachersListContract.Presenter>(), TeachersListContract.View{

    override val presenter: TeachersListContract.Presenter? by inject { parametersOf(this) }
    private lateinit var binding: ActivityTeachersListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeachersListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}