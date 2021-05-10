package com.example.ratemyteacher.ui.reviewslist

import org.koin.core.KoinComponent

/**
 * @author  Csongor Nagy
 * @since  10.05.2021
 */
class ReviewsListPresenter(view: ReviewsListContract.View) : ReviewsListContract.Presenter(view),
    KoinComponent {
}