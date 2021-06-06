package com.example.ratemyteacher.ui.splash

import android.os.CountDownTimer
import org.koin.core.KoinComponent

/**
 * @author  Csongor Nagy
 * @since  10.05.2021
 */
class SplashPresenter(view: SplashContract.View): SplashContract.Presenter(view), KoinComponent {
    override fun startCounter() {
        object : CountDownTimer(2000,1000){
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                view?.startApplication()
            }

        }.start()
    }
}