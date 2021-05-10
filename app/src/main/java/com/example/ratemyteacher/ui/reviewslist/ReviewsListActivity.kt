package com.example.ratemyteacher.ui.reviewslist

import android.os.Bundle
import com.example.ratemyteacher.databinding.ActivityReviewsListBinding
import com.example.ratemyteacher.ui.base.BaseActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ReviewsListActivity : BaseActivity<ReviewsListContract.Presenter>(), ReviewsListContract.View{

    override val presenter: ReviewsListContract.Presenter? by inject { parametersOf(this) }
    private lateinit var binding: ActivityReviewsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}