package com.example.ratemyteacher.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.example.ratemyteacher.R
import com.example.ratemyteacher.databinding.ActivityLoginBinding
import com.example.ratemyteacher.ui.base.BaseActivity
import com.example.ratemyteacher.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.util.regex.Matcher
import java.util.regex.Pattern


class LoginActivity : BaseActivity<LoginContract.Presenter>(), LoginContract.View{

    override val presenter: LoginContract.Presenter by inject{ parametersOf(this) }
    private lateinit var binding: ActivityLoginBinding
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance();
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email=findViewById<EditText>(R.id.editTextEmailAddress)
        val password=findViewById<EditText>(R.id.editTextPassword)
        val reEnteredPassword=findViewById<EditText>(R.id.editTextTextReenteredPassword)

        binding.button.setOnClickListener {
            signInExistingUser(email,password)
        }


        binding.signUpButton.setOnClickListener{
            if (checkIfEmailMatches(email) && checkIfPasswordsMatch(password,reEnteredPassword)) {
                signUpUser(email,password)
            }
        }
    }

    private fun checkIfEmailMatches(email: EditText): Boolean {
        val email: String = email.getText().toString()
        if (email.length != 0) {
            if (isValidEmail(email)) {
                Toast.makeText(applicationContext, "Valid email address!", Toast.LENGTH_SHORT)
                    .show()
                return true
            } else {
                Toast.makeText(applicationContext, "Invalid email!", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
        } else {
            Toast.makeText(applicationContext, "Email required!", Toast.LENGTH_SHORT)
                .show()
            return false
        }
    }

    private fun checkIfPasswordsMatch(password: EditText,passwordReEntered: EditText): Boolean {
        if (isValidPassword(password.getText().toString())) {
            if (password.text.toString().equals(passwordReEntered.text.toString())) {
                Toast.makeText(this, "Password ok", Toast.LENGTH_SHORT).show()
                return true
            } else {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return false
            }
        } else {
            Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    fun signInUser(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun signInExistingUser(email: EditText,password: EditText) {
        mAuth!!.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("SignInSuccessful", "signInWithEmail:success")
                    val user = mAuth!!.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("SignInFailure", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
    override fun onStart() {
        super.onStart()
        val currentUser = mAuth!!.currentUser
        if (currentUser != null){
            signInUser()
        }
    }

    fun signUpUser(email: EditText,password: EditText){
        mAuth!!.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Success", "createUserWithEmail:success")
                    val user = mAuth!!.currentUser
                    signInUser()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(
                        "Failure",
                        "createUserWithEmail:failure",
                        task.exception
                    )
                    print(task.exception!!.localizedMessage)
                    Toast.makeText(
                        this, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                // ...
            }
    }
    fun isValidPassword(password: String?): Boolean {
        val PASSWORD_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}"
        )
        return !TextUtils.isEmpty(password) && PASSWORD_PATTERN.matcher(password).matches()
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}