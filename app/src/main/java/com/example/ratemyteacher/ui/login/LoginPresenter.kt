package com.example.ratemyteacher.ui.login

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import org.koin.core.KoinComponent

/**
 * @author  Csongor Nagy
 * @since  10.05.2021
 */
class LoginPresenter(view: LoginContract.View) : LoginContract.Presenter(view), KoinComponent {

    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun login(email: String, password: String, activity: AppCompatActivity) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("SignInSuccessful", "signInWithEmail:success")
                    val user = mAuth.currentUser
                    view?.startMainActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("SignInFailure", "signInWithEmail:failure", task.exception)
                    view?.showText("Authentication failed.")
                }
            }
    }

    override fun checkLoginState() {
        if (mAuth.currentUser != null) {
            view?.startMainActivity()
        }
    }
}