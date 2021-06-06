package com.example.ratemyteacher.ui.reviewslist

import android.util.Log
import android.widget.ArrayAdapter
import com.example.ratemyteacher.R
import com.example.ratemyteacher.ui.rateteacher.Review
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.koin.core.KoinComponent
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author  Csongor Nagy
 * @since  10.05.2021
 */
class ReviewsListPresenter(view: ReviewsListContract.View) : ReviewsListContract.Presenter(view),
    KoinComponent {

    private val database = FirebaseDatabase.getInstance().reference
    private val reference = database.child("App").child("Teachers").child("Departments")

    override fun getCategories() {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.d("Response", error.message)

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var categories: ArrayList<String> = ArrayList()
                val teachers: ArrayList<String> = ArrayList()

                categories.add("All")

                for (ds in snapshot.children) {
                    categories.add(ds.key.toString())
                    for (child in ds.children) {
                        teachers.add(child.value.toString())
                    }

                }
                view?.setupDepartmentSpinner(categories)
                view?.setUpTeacherSpinner(teachers)
            }
        })
    }

    override fun getReviews(teacher: String) {
        database.child("App").child("Reviews").child(teacher.hashCode().toString())
            .addListenerForSingleValueEvent(object : ValueEventListener {
                val reviews: ArrayList<Review> = ArrayList()
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (ds in snapshot.children) {
                        val review = ds.getValue(Review::class.java)
                        review?.let {  reviews.add(it) }
                    }
                    view?.showReviews(reviews)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("Response", error.message)
                }
            })
    }

    override fun getTeachers() {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.d("Response", error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val teachers: ArrayList<String> = ArrayList()
                val selectedItem = view?.getSelectedDepartment()
                for (ds in snapshot.children) {
                    for (child in ds.children) {
                        if (selectedItem != "All") {
                            if (ds.key == selectedItem) {
                                teachers.add(child.value.toString())
                            }
                        } else {
                            teachers.add(child.value.toString())
                        }
                    }
                }
                view?.setUpTeacherSpinner(teachers)
            }
        })
    }
}