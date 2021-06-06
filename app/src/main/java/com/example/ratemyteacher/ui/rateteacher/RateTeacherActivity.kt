package com.example.ratemyteacher.ui.rateteacher

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.ratemyteacher.R
import com.example.ratemyteacher.databinding.ActivityReatTeacherBinding
import com.example.ratemyteacher.ui.base.BaseActivity
import com.example.ratemyteacher.ui.profile.ProfileActivity
import com.example.ratemyteacher.ui.reviewslist.ReviewsListActivity
import com.example.ratemyteacher.ui.teacherslist.TeachersListActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.util.*
import kotlin.collections.ArrayList


class RateTeacherActivity : BaseActivity<RateTeacherContract.Presenter>(), RateTeacherContract.View{

    override val presenter: RateTeacherContract.Presenter? by inject { parametersOf(this) }
    private lateinit var binding: ActivityReatTeacherBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReatTeacherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.teachersImageView.setOnClickListener {
            startActivity(Intent(this,TeachersListActivity::class.java))
            finish()
        }

        binding.reviewsImageView.setOnClickListener {
            startActivity(Intent(this,ReviewsListActivity::class.java))
            finish()
        }

        binding.profileImageView.setOnClickListener {
            startActivity(Intent(this,ProfileActivity::class.java))
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
                val spinner = binding.departmentSpinner
                if (spinner != null) {
                    val adapter = ArrayAdapter(
                        this@RateTeacherActivity,
                        R.layout.support_simple_spinner_dropdown_item, categories
                    )
                    spinner.adapter = adapter
                }

                val spinnerTeachers = binding.teacherSpinner
                if (spinnerTeachers != null) {
                    val adapter = ArrayAdapter(
                        this@RateTeacherActivity,
                        R.layout.support_simple_spinner_dropdown_item, teachers
                    )
                    spinnerTeachers.adapter = adapter
                }
            }
        })
        binding.saveReviewButton.setOnClickListener {
            if (!binding.reviewTextField.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "Saved", Toast.LENGTH_SHORT)
                    .show()
                database = FirebaseDatabase.getInstance().reference
                val currentUser = FirebaseAuth.getInstance().currentUser?.email?:""

                database.child("App").child("Reviews").child(binding.teacherSpinner.selectedItem.hashCode().toString()).push().setValue(Review(currentUser,binding.teacherSpinner.selectedItem.toString(),binding.departmentSpinner.selectedItem.toString(),binding.reviewTextField.text.toString()))
            } else {
                Toast.makeText(applicationContext, "Please complete the review field !", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.departmentSpinner.onItemSelectedListener = object : OnItemSelectedListener {
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
                        var selectedItem = binding.departmentSpinner.selectedItem.toString()


                        for (ds in snapshot.children) {
                            for (child in ds.children) {
                                if (selectedItem != "All") {
                                    if (ds.key == binding.departmentSpinner.selectedItem.toString()) {
                                        teachers.add(child.value.toString())
                                    }
                                } else {
                                    teachers.add(child.value.toString())
                                }
                            }
                        }
                        val spinnerTeachers = binding.teacherSpinner
                        if (spinnerTeachers != null) {
                            val adapter = ArrayAdapter(
                                this@RateTeacherActivity,
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