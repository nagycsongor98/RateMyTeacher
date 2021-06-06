package com.example.ratemyteacher.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.example.ratemyteacher.databinding.ActivityLoginBinding
import com.example.ratemyteacher.databinding.ActivitySignUpBinding
import com.example.ratemyteacher.ui.base.BaseActivity
import com.example.ratemyteacher.ui.login.LoginActivity
import com.example.ratemyteacher.ui.login.LoginContract
import com.example.ratemyteacher.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.util.regex.Pattern

class SignUpActivity : BaseActivity<SignUpContract.Presenter>(), SignUpContract.View{

    override val presenter: SignUpContract.Presenter by inject{ parametersOf(this) }
    private lateinit var binding: ActivitySignUpBinding
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance();
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpButton.setOnClickListener {
            presenter.signUp(
                binding.editTextEmailAddress.text.toString(),
                binding.editTextPassword.text.toString(),
                binding.editTextTextReenteredPassword.text.toString(),
                this
            )
        }

        binding.loginTextView.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }

    override fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}