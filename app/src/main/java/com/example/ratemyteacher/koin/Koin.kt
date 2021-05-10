package com.example.ratemyteacher.koin

import com.example.ratemyteacher.ui.login.LoginContract
import com.example.ratemyteacher.ui.login.LoginPresenter
import com.example.ratemyteacher.ui.main.MainContract
import com.example.ratemyteacher.ui.main.MainPresenter
import com.example.ratemyteacher.ui.profile.ProfileContract
import com.example.ratemyteacher.ui.profile.ProfilePresenter
import com.example.ratemyteacher.ui.rateteacher.RateTeacherContract
import com.example.ratemyteacher.ui.rateteacher.RateTeacherPresenter
import com.example.ratemyteacher.ui.reviewslist.ReviewsListContract
import com.example.ratemyteacher.ui.reviewslist.ReviewsListPresenter
import com.example.ratemyteacher.ui.splash.SplashContract
import com.example.ratemyteacher.ui.splash.SplashPresenter
import com.example.ratemyteacher.ui.teacherslist.TeachersListContract
import com.example.ratemyteacher.ui.teacherslist.TeachersListPresenter
import org.koin.dsl.module

/**
 * @author  Csongor Nagy
 * @since  14.04.2021
 */

val uiModule = module {
    factory { (view: SplashContract.View) -> SplashPresenter(view) as SplashContract.Presenter }
    factory { (view: LoginContract.View) -> LoginPresenter(view) as LoginContract.Presenter }
    factory { (view: MainContract.View) -> MainPresenter(view) as MainContract.Presenter }
    factory { (view: RateTeacherContract.View) -> RateTeacherPresenter(view) as RateTeacherContract.Presenter }
    factory { (view: TeachersListContract.View) -> TeachersListPresenter(view) as TeachersListContract.Presenter }
    factory { (view: ReviewsListContract.View) -> ReviewsListPresenter(view) as ReviewsListContract.Presenter }
    factory { (view: ProfileContract.View) -> ProfilePresenter(view) as ProfileContract.Presenter }
}