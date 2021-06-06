package com.example.ratemyteacher.ui.login

import android.content.Intent
import android.os.Bundle
import com.example.ratemyteacher.databinding.ActivityLoginBinding
import com.example.ratemyteacher.ui.base.BaseActivity
import com.example.ratemyteacher.ui.main.MainActivity
import com.example.ratemyteacher.ui.signup.SignUpActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class LoginActivity : BaseActivity<LoginContract.Presenter>(), LoginContract.View {

    override val presenter: LoginContract.Presenter by inject { parametersOf(this) }
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            presenter.login(
                binding.editTextEmailAddress.text.toString(),
                binding.editTextPassword.text.toString(),
                this
            )
        }

        binding.signUpTextView.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }

    override fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onStart() {
        super.onStart()
        presenter.checkLoginState()
    }
}