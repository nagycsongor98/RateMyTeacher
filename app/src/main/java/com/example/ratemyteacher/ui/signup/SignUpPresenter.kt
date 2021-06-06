package com.example.ratemyteacher.ui.signup

import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import org.koin.core.KoinComponent
import java.util.regex.Pattern

/**
 * @author  Csongor Nagy
 * @since  06.06.2021
 */
class SignUpPresenter(view: SignUpContract.View) : SignUpContract.Presenter(view), KoinComponent {

    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun signUp(
        email: String,
        password: String,
        reenteredPassword: String,
        activity: AppCompatActivity
    ) {
        if (checkIfEmailMatches(email) && checkIfPasswordsMatch(password, reenteredPassword)) {
            signUpUser(email, password,activity)
        }
    }

    private fun signUpUser(email: String, password: String, activity: AppCompatActivity){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                activity
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Success", "createUserWithEmail:success")
                    view?.startMainActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(
                        "Failure",
                        "createUserWithEmail:failure",
                        task.exception
                    )
                    print(task.exception!!.localizedMessage)
                    view?.showText("Authentication failed.")
                }
            }
    }

    private fun checkIfEmailMatches(email: String): Boolean {
        return if (email.isNotEmpty()) {
            if (isValidEmail(email)) {
                view?.showText("Valid email address!")
                true
            } else {
                view?.showText("Invalid email!")
                false
            }
        } else {
            view?.showText("Email required!")
            false
        }
    }

    private fun checkIfPasswordsMatch(password: String, passwordReEntered: String): Boolean {
        return if (isValidPassword(password)) {
            if (password == passwordReEntered) {
                view?.showText("Password ok")
                true
            } else {
                view?.showText("Passwords do not match")
                false
            }
        } else {
            view?.showText("Invalid password")
            false
        }
    }

    private fun isValidPassword(password: String?): Boolean {
        val PASSWORD_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}"
        )
        return !TextUtils.isEmpty(password) && PASSWORD_PATTERN.matcher(password).matches()
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}