package com.example.ratemyteacher.ui.reviewslist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ratemyteacher.R
import com.example.ratemyteacher.databinding.ActivityReviewsListBinding
import com.example.ratemyteacher.ui.base.BaseActivity
import com.example.ratemyteacher.ui.profile.ProfileActivity
import com.example.ratemyteacher.ui.rateteacher.RateTeacherActivity
import com.example.ratemyteacher.ui.rateteacher.Review
import com.example.ratemyteacher.ui.teacherslist.TeachersListActivity
import com.google.firebase.database.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.util.*
import kotlin.collections.ArrayList

class ReviewsListActivity : BaseActivity<ReviewsListContract.Presenter>(), ReviewsListContract.View{

    override val presenter: ReviewsListContract.Presenter? by inject { parametersOf(this) }
    private lateinit var binding: ActivityReviewsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rateImageView.setOnClickListener {
            startActivity(Intent(this, RateTeacherActivity::class.java))
            finish()
        }

        binding.teachersImageView.setOnClickListener {
            startActivity(Intent(this, TeachersListActivity::class.java))
            finish()
        }

        binding.profileImageView.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }

        presenter?.getCategories()

        binding.departmentSpinnerReview.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                i: Int,
                l: Long
            ) {
                presenter?.getTeachers()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                return
            }
        }

        binding.teacherSpinnerReview.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                i: Int,
                l: Long
            ) {
                presenter?.getReviews(binding.teacherSpinnerReview.selectedItem.toString())
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                return
            }
        }
    }

    override fun getSelectedDepartment(): String {
        return  binding.departmentSpinnerReview.selectedItem.toString()
    }

    override fun setUpTeacherSpinner(teachers: ArrayList<String>) {
        val adapter = ArrayAdapter(
            this@ReviewsListActivity,
            R.layout.support_simple_spinner_dropdown_item, teachers
        )
        binding.teacherSpinnerReview.adapter = adapter
    }

    override fun setupDepartmentSpinner(categories: ArrayList<String>) {
        var mCategories = categories
        if (mCategories.size == 0) {
            mCategories = ArrayList(listOf(*resources.getStringArray(R.array.Categories)))
        }
        val adapter = ArrayAdapter(
            this@ReviewsListActivity,
            R.layout.support_simple_spinner_dropdown_item, mCategories
        )
        binding.departmentSpinnerReview.adapter = adapter
    }

    override fun showReviews(reviews: ArrayList<Review>) {
        binding.reviewsRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.reviewsRecyclerView.adapter =
            ReviewAdapter(reviews)
    }
}