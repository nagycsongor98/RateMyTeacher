package com.example.ratemyteacher.ui.reviewslist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.ratemyteacher.R
import com.example.ratemyteacher.databinding.ActivityReviewsListBinding
import com.example.ratemyteacher.ui.base.BaseActivity
import com.example.ratemyteacher.ui.profile.ProfileActivity
import com.example.ratemyteacher.ui.rateteacher.RateTeacherActivity
import com.example.ratemyteacher.ui.teacherslist.TeachersListActivity
import com.google.firebase.database.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.util.*
import kotlin.collections.ArrayList

class ReviewsListActivity : BaseActivity<ReviewsListContract.Presenter>(), ReviewsListContract.View{

    override val presenter: ReviewsListContract.Presenter? by inject { parametersOf(this) }
    private lateinit var binding: ActivityReviewsListBinding
    private lateinit var database: DatabaseReference


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

        database = FirebaseDatabase.getInstance().reference
        var reference = database.child("App").child("Teachers").child("Departments")

        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.d("Response", error.message)

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var categories: ArrayList<String> = ArrayList()
                var teachers: ArrayList<String> = ArrayList()

                categories.add("All")

                for (ds in snapshot.children) {
                    categories.add(ds.key.toString())
                    for (child in ds.children) {
                        teachers.add(child.value.toString())
                    }

                }
                if (categories.size == 0) {
                    categories = ArrayList<String>(Arrays.asList(*resources.getStringArray(R.array.Categories)))
                }
                val spinner = binding.departmentSpinnerReview
                if (spinner != null) {
                    val adapter = ArrayAdapter(
                        this@ReviewsListActivity,
                        R.layout.support_simple_spinner_dropdown_item, categories
                    )
                    spinner.adapter = adapter
                }

                val spinnerTeachers = binding.teacherSpinnerReview
                if (spinnerTeachers != null) {
                    val adapter = ArrayAdapter(
                        this@ReviewsListActivity,
                        R.layout.support_simple_spinner_dropdown_item, teachers
                    )
                    spinnerTeachers.adapter = adapter
                }
            }
        })

        binding.departmentSpinnerReview.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                i: Int,
                l: Long
            ) {
                reference.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                        Log.d("Response", error.message)

                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        var teachers: ArrayList<String> = ArrayList()
                        var selectedItem = binding.departmentSpinnerReview.selectedItem.toString()


                        for (ds in snapshot.children) {
                            for (child in ds.children) {
                                if (selectedItem != "All") {
                                    if (ds.key == binding.departmentSpinnerReview.selectedItem.toString()) {
                                        teachers.add(child.value.toString())
                                    }
                                } else {
                                    teachers.add(child.value.toString())
                                }
                            }
                        }
                        val spinnerTeachers = binding.teacherSpinnerReview
                        if (spinnerTeachers != null) {
                            val adapter = ArrayAdapter(
                                this@ReviewsListActivity,
                                R.layout.support_simple_spinner_dropdown_item, teachers
                            )
                            spinnerTeachers.adapter = adapter
                        }
                    }
                })

            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                return
            }
        }
    }
}