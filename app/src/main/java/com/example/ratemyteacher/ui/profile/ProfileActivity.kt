package com.example.ratemyteacher.ui.profile

import android.os.Bundle
import com.example.ratemyteacher.databinding.ActivityProfileBinding
import com.example.ratemyteacher.ui.base.BaseActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ProfileActivity : BaseActivity<ProfileContract.Presenter>(), ProfileContract.View{

    override val presenter: ProfileContract.Presenter? by inject { parametersOf(this) }
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}