package com.example.ratemyteacher.ui.rateteacher

import com.example.ratemyteacher.ui.main.MainContract
import org.koin.core.KoinComponent

/**
 * @author  Csongor Nagy
 * @since  10.05.2021
 */
class RateTeacherPresenter(view: RateTeacherContract.View): RateTeacherContract.Presenter(view), KoinComponent {
}